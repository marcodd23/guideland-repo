package it.guideland.app.config;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.guideland.app.config.root.JpaDevelopmentConfiguration;
import it.guideland.app.config.root.RootContextConfig;
import it.guideland.app.config.servlet.ServletContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@WebAppConfiguration
@ContextConfiguration(classes = { RootContextConfig.class, JpaDevelopmentConfiguration.class,
		ServletContextConfig.class })
public class WebAppConfigurationAware {
	@Inject
	protected WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
}
