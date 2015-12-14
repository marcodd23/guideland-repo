package it.guideland.app.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.security.crypto.codec.Base64;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class RestLoginFilter extends AbstractAuthenticationProcessingFilter {

	Logger logger = LoggerFactory.getLogger(RestLoginFilter.class);

	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";

	@Autowired
	private RestAuthenticationService restAuthenticationService;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	public RestLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager,
			RestAuthenticationFailureHandler restAuthenticationFailureHandler,
			RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
			RestAuthenticationService restAuthenticationService, UserDetailsServiceImpl userDetailsServiceImpl) {
		super(defaultFilterProcessesUrl);
		// this.restAuthenticationService = restAuthenticationService;
		// this.userDetailsService = userDetailsService;
		setAuthenticationManager(authenticationManager);
		setAuthenticationFailureHandler(restAuthenticationFailureHandler);
		setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
		this.restAuthenticationService = restAuthenticationService;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		logger.debug(">>>>>>>>>>>>>>>>>>>>>> RestLoginFilter.attempthAuthentication <<<<<<<<<<<<<<<<<<<<<<");
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		String username = request.getHeader(HEADER_USERNAME);
		String password = request.getHeader(HEADER_PASSWORD);
		UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(null, null);
		if (authorization != null) {
			loginToken = checkBasicAuthorization(authorization, response);
		} else if (username != null && password != null) {
			loginToken = new UsernamePasswordAuthenticationToken(username, password);
		}
		return getAuthenticationManager().authenticate(loginToken);
	}

	private UsernamePasswordAuthenticationToken checkBasicAuthorization(String authorization,
			HttpServletResponse response) {

		logger.debug(">>>>>>>>>>>>>>>>>>>>>> RestLoginFilter.checkBasicAuthorization <<<<<<<<<<<<<<<<<<<<<<");
		StringTokenizer tokenizer = new StringTokenizer(authorization);
		UsernamePasswordAuthenticationToken loginToken;

		if (tokenizer.countTokens() < 2 || !tokenizer.nextToken().equalsIgnoreCase("Basic")) {
			loginToken = new UsernamePasswordAuthenticationToken(null, null);
			return loginToken;
		}

		String base64 = tokenizer.nextToken();
		String usernameAndPassword = new String(Base64.decode(base64.getBytes(StandardCharsets.UTF_8)));
		tokenizer = new StringTokenizer(usernameAndPassword, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		loginToken = new UsernamePasswordAuthenticationToken(username, password);
		return loginToken;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {

		logger.debug(">>>>>>>>>>>>>>>>>>>>>> RestLoginFilter.successfulAuthentication <<<<<<<<<<<<<<<<<<<<<<");
		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl
				.loadUserByUsername(authentication.getName());
		restAuthenticationService.addAuthenticationToken(response, userDetails);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		getSuccessHandler().onAuthenticationSuccess(request, response, authentication);
	}

	/*
	 * @Override protected void unsuccessfulAuthentication(HttpServletRequest
	 * request, HttpServletResponse response, AuthenticationException failed)
	 * throws IOException, ServletException { // TODO Auto-generated method stub
	 * super.unsuccessfulAuthentication(request, response, failed); }
	 */

}
