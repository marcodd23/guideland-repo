package it.guideland.app.config;

import javax.inject.Inject;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class WebSecurityConfigurationAware extends WebAppConfigurationAware {

	protected static final String HEADER_USERNAME = "X-Username";
	protected static final String HEADER_PASSWORD = "X-Password";
	protected static final String HEADER_TOKEN = "X-Auth-Token";
	
	@Inject
	private FilterChainProxy springSecurityFilterChain;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.addFilters(this.springSecurityFilterChain).build();
	}
}
