package it.guideland.app.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.web.servlet.MvcResult;

import it.guideland.app.config.WebSecurityConfigurationAware;
import it.guideland.app.utility.TestUtility;

public class RestAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

	Logger logger = LoggerFactory.getLogger(RestAuthenticationIntegrationTest.class);
	
/*	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";
	private static final String HEADER_TOKEN = "X-Auth-Token";*/
	
	@Autowired
	private TokenManager tokenManager;

	//============================================ WRONG LOGINS ===========================================
	
	
	@Test
	public void postLoginWithWrongCredentialsAuthorizationHeaderTest() throws Exception {
		mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, TestUtility.createAuthorizationHeader("username-prova-1", "incorrectpassword"))
				).andExpect(status().isUnauthorized());
	}

	
	@Test
	public void getLoginWithWrongCredentialsAuthorizationHeaderTest() throws Exception {
		mockMvc.perform(get("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, TestUtility.createAuthorizationHeader("username-prova-1", "incorrectpassword"))
				).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void postLoginWithWrongCredentialsUsernamPasswordHeaderTest() throws Exception {
		mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HEADER_USERNAME, "username-prova-1")
				.header(HEADER_PASSWORD, "incorrectpassword")
				).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void getLoginWithWrongCredentialsUsernamPasswordHeaderTest() throws Exception {
		mockMvc.perform(get("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HEADER_USERNAME, "username-prova-1")
				.header(HEADER_PASSWORD, "incorrectpassword")
				).andExpect(status().isUnauthorized());
	}
	//============================================ CORRECT LOGINS ===========================================
	

	@Test
	public void postLoginWithCorrectCredentialsAuthorizationHeaderTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, TestUtility.createAuthorizationHeader("username-prova-1", "password1"))
				).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        String token = mvcResult.getResponse().getHeader(HEADER_TOKEN);
		Assert.assertNotNull(token);
		if(token != null){
			Assert.assertNotNull(tokenManager.validateUserFromToken(token));
		}
	}
	
	@Test
	public void getLoginWithCorrectCredentialsAuthorizationHeaderTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, TestUtility.createAuthorizationHeader("username-prova-1", "password1"))
				).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        String token = mvcResult.getResponse().getHeader(HEADER_TOKEN);
		Assert.assertNotNull(token);
		if(token != null){
			Assert.assertNotNull(tokenManager.validateUserFromToken(token));
		}
	}

	//============================================ LOGINS with no header ===========================================
	
	@Test
	public void postLoginWithNoHeaderTest() throws Exception {
		mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isUnauthorized());
	}

	@Test
	public void getLoginWithNoHeaderTest() throws Exception {
		mockMvc.perform(post("/api/login")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isUnauthorized());
	}
	

/*	private String createAuthorizationHeader(String username, String password) {

		String userCredentials = username + ":" + password;
		//new Base64();
		String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(StandardCharsets.UTF_8)));
		return basicAuth;
	}*/

}
