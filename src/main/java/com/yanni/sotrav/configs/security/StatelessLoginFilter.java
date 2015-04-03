package com.yanni.sotrav.configs.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yanni.sotrav.models.User;
import com.yanni.sotrav.models.UserAuthentication;
import com.yanni.sotrav.services.User.BaseUserService;
import com.yanni.sotrav.services.token.TokenAuthenticationService;

class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userService;

	protected StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			UserDetailsService uService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userService = uService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}
	
//	private void addUsers() {
//		BaseUserService bs=(BaseUserService)userService;
//		List<User> usrs=(List) bs.findAll();
//			for(User usr:usrs){
//				String pword=usr.getUser_pass();
//				usr.setUser_pass(new BCryptPasswordEncoder().encode(pword));
//				bs.Update(usr);
//			}
//	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String usr=request.getParameter("username")!=null?request.getParameter("username"):request.getHeader("username");
		String pword=request.getParameter("password")!=null?request.getParameter("password"):request.getHeader("password");
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(usr, pword);
		AuthenticationManager authman=getAuthenticationManager();
		Authentication isauth=null;
		try{	
			isauth=authman.authenticate(loginToken);
		}catch(AuthenticationException e){
			if(request.getParameter("client")!=null || request.getHeader("client")!=null){
				response.sendRedirect(request.getContextPath() + "/login.html?error=wrong");
			}else{
				throw e;
			}
		}
		return isauth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an Authentication for it
		System.out.println("the name is "+authentication.getName());
		User usr=(User)authentication.getPrincipal();
		final User authenticatedUser = (User) userService.loadUserByUsername(usr.getUser_email());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
		HttpSession session=request.getSession();
		session.setAttribute("userLogin", usr);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		if(request.getParameter("client")!=null || request.getHeader("client")!=null){
			response.sendRedirect(request.getContextPath() + "/nav/admin.html");
		}
	}
}