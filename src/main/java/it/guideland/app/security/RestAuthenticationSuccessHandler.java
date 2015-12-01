package it.guideland.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>> RestAuthenticationSuccessHandler.onAuthenticationSuccess <<<<<<<<<<<<<<<<<<<<<<");
		response.setStatus(HttpServletResponse.SC_OK);
        clearAuthenticationAttributes(request);
	}


}
