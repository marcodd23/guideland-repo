package it.guideland.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


public class RestLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";
	
	@Autowired
	private RestAuthenticationService restAuthenticationService;
	
	@Autowired
	private UserDetailsService userDetailsService;
		
	public RestLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		//this.restAuthenticationService = restAuthenticationService;
		//this.userDetailsService = userDetailsService;
		setAuthenticationManager(authenticationManager);
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String username = request.getHeader(HEADER_USERNAME);
		String password = request.getHeader(HEADER_PASSWORD);
		UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(username, password);			
		return getAuthenticationManager().authenticate(loginToken);
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(authentication.getName());
		restAuthenticationService.addAuthenticationToken(response, userDetails);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}


	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.unsuccessfulAuthentication(request, response, failed);
	}
	

}
