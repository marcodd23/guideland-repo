package it.guideland.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		/*response.addHeader("Access-Control-Allow-Origin", "null");
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");*/
		logger.debug(" ====================== TRIGGER RestAuthenticationEntryPoint ======================");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
