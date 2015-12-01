package it.guideland.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	Logger logger = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>> RestAuthenticationFailureHandler.onAuthenticationFailure <<<<<<<<<<<<<<<<<<<<<<");
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad credentials");
	}

}
