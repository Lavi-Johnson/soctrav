package com.yanni.sotrav.configs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanni.sotrav.manager.UserManager;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.models.UserAuthentication;
import com.yanni.sotrav.services.User.BaseUserService;
import com.yanni.sotrav.services.token.TokenAuthenticationService;

class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userService;
	
	private UserRepository userRepository;

	private void addUsers() {
		BaseUserService bs=(BaseUserService)userService;
		List<User> usrs=(List) bs.findAll();
			for(User usr:usrs){
				userRepository.save(usr);
			}
	}

	protected StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			UserDetailsService uService, AuthenticationManager authManager, UserRepository u) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userService = uService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.userRepository=u;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		//final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		String usr=request.getParameter("username");
		final User user = (User) userService.loadUserByUsername(usr);
		addUsers();
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				user.getUser_name(), user.getUser_pass());
		return getAuthenticationManager().authenticate(loginToken);
	}
//	
//	public class Authority implements GrantedAuthority{
//		
//		String authority="";
//		
//		public Authority(String auth){
//			authority=auth;
//		}
//		
//		@Override
//		public String getAuthority() {
//			return authority;
//		}
//		
//	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an Authentication for it
		final User authenticatedUser = (User) userService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
}