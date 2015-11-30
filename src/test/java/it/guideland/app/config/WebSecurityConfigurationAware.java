package it.guideland.app.config;

import javax.inject.Inject;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class WebSecurityConfigurationAware extends WebAppConfigurationAware {

	@Inject
	private FilterChainProxy springSecurityFilterChain;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.addFilters(this.springSecurityFilterChain).build();
	}
}
