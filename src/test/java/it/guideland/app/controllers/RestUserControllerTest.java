package it.guideland.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.guideland.app.config.WebSecurityConfigurationAware;
import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.security.TokenData;
import it.guideland.app.security.TokenManager;
import it.guideland.app.utility.TestUtility;

public class RestUserControllerTest extends WebSecurityConfigurationAware {

	private Logger logger = LoggerFactory.getLogger(RestUserControllerTest.class);
	
	@Autowired
	private TokenManager tokenManager;

	@Test
	public void getCurrentUserTest() throws Exception {

		MvcResult loginResult = mockMvc
				.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,
						TestUtility.createAuthorizationHeader("username-prova-1", "password1")))
				.andReturn();

		String token = loginResult.getResponse().getHeader(HEADER_TOKEN);
		if (token != null) {
			TokenData tokenData = tokenManager.validateUserFromToken(token);

			mockMvc.perform(
					get("/api/user/current").contentType(MediaType.APPLICATION_JSON).header(HEADER_TOKEN, token))
					.andExpect(status().is2xxSuccessful())
					.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(tokenData.getUsername())))
					.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Marco")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Di Dionisio")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.mobileNumber", Matchers.is("333-7687999")));
		}else {
			logger.error("TOKEN IS NULL !!!! ");
		}

	}
	
/*	@Test
	public void createUserTest() throws Exception {

		UserRegistrationDTO userDTO = new UserRegistrationDTO();
		userDTO.setName("tizio");
		userDTO.setSurname("sempronio");
		userDTO.setSurname("testUsername");
		userDTO.setPassowrd("123456");
		userDTO.setConfirmPassword("654321");
		userDTO.setEmail("fgdfg@fdg.it");
		userDTO.setConfirmEmail("dfgd@asd.it");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(userDTO); 
		
		MvcResult result = mockMvc
				.perform(post("/api/user/create").contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
				.andReturn();

		Assert.assertTrue(result.getResponse().getStatus()==400);
		
		logger.error(result.getResponse().getContentAsString());

	}*/
}
