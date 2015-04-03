package com.yanni.sotrav.services.token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.yanni.sotrav.common.TokenHandler;
import com.yanni.sotrav.models.User;
import com.yanni.sotrav.models.UserAuthentication;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private static final String AUTH_Cookie_NAME= "X-AUTH-Cookie-Tok";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws IOException {
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + TEN_DAYS);
		String token=tokenHandler.createTokenForUser(user);
		Cookie cookie = new Cookie(AUTH_Cookie_NAME+"_"+System.currentTimeMillis(),token);
		cookie.setPath("/");
//		cookie.setMaxAge((int) (System.currentTimeMillis() + TEN_DAYS));
		response.addCookie(cookie);
		response.addHeader(AUTH_HEADER_NAME, token);
		response.getOutputStream().write(tokenHandler.toByte(token));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String chkTok = request.getHeader(AUTH_HEADER_NAME);
		if(chkTok==null && request.getCookies()!=null){
			Cookie[] cookies=request.getCookies();
			//Collection<Cookie> cookietok = new ArrayList();
			long latest=-2;
			for (int i=0;i<cookies.length;i++) {
				Cookie co=cookies[i];
				if(co.getName().contains(AUTH_Cookie_NAME)){
					String nameTime[]=co.getName().split("_");
					long time=0;
					if(nameTime.length>1){
						time=Long.parseLong(nameTime[1]);
					}
					if(time>latest){
						latest=time;
						chkTok =co.getValue();
					}
				}
			}
		}
		final String token =chkTok;
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				HttpSession session=request.getSession();
				session.setAttribute("userLogin", user);
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
