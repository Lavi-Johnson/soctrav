package com.yanni.sotrav.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yanni.sotrav.services.token.TokenAuthenticationService;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService baseUserService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//      authorizeRequests()
//			.antMatchers("/", "/home", "/rest/getToken").permitAll()
//	        .antMatchers("/resource/**").permitAll()
//	        .anyRequest().authenticated().and()
		.exceptionHandling().and()
		.anonymous().and()
		.servletApi().and()
		.headers().cacheControl().and()
		.authorizeRequests()
		.antMatchers("/", "/home", "/rest/getToken", "/login.html").permitAll()
	    .antMatchers("/resource/**").permitAll()
	    .antMatchers(HttpMethod.POST, "/api/login").permitAll()
	    //.anyRequest().hasRole("USER").and()		
		//allow anonymous resource requests
		.antMatchers(HttpMethod.GET,"/resource/**").permitAll()
		.antMatchers(HttpMethod.GET,"/").permitAll()
		
		//allow anonymous GETs to API
		//.antMatchers(HttpMethod.GET, "/api/**").permitAll()
		
		//defined Admin only API area
		.antMatchers("/admin/**").hasRole("ADMIN")
		
		//all other request need to be authenticated
		.anyRequest().anonymous().and()		
				// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, baseUserService, authenticationManager(), userRepository), UsernamePasswordAuthenticationFilter.class)

		// custom Token based authentication based on the header previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
//		.authorizeRequests()
//		.anyRequest().authenticated().and()
//        .formLogin()
//           	.defaultSuccessUrl("/nav/admin.html")
//               .loginPage("/login.html")
//               .permitAll()
//        .and()
//           .logout()
//           	.logoutSuccessUrl("/login.html?logout")
//           	.logoutUrl("/logout")
//            .permitAll();

	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(baseUserService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return baseUserService;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("user@gmail.com").password("password").roles("USER");
	}
}
