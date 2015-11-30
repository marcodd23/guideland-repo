package it.guideland.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class RestAuthenticationFilter extends GenericFilterBean {

	Logger logger = LoggerFactory.getLogger(RestAuthenticationFilter.class);
	
	private RestAuthenticationService restAuthenticationService;

	public RestAuthenticationFilter(RestAuthenticationService restAuthenticationService) {
		super();
		this.restAuthenticationService = restAuthenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.debug(" >>>>>>>>>>>>>>>>>>>>>>>>>>> RestAuthenticationFilter.doFilter <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		SecurityContextHolder.getContext()
				.setAuthentication(restAuthenticationService.getAuthentication((HttpServletRequest) request));
		chain.doFilter(request, response);
	}

}
