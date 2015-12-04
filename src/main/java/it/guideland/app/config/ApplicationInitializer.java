package it.guideland.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import it.guideland.app.config.root.JpaDevelopmentConfiguration;
import it.guideland.app.config.root.JpaTestConfiguration;
import it.guideland.app.config.root.RootContextConfig;
import it.guideland.app.config.security.AppSecurityConfig;
import it.guideland.app.config.servlet.ServletContextConfig;


public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { RootContextConfig.class, JpaDevelopmentConfiguration.class,
				JpaTestConfiguration.class, AppSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { ServletContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	
	

	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);

		// per settare il profilo attivo
		servletContext.setInitParameter("spring.profiles.active", "test");

		// Set multiple active profile
		// servletContext.setInitParameter("spring.profiles.active",
		// "development, testdb");
	}*/

}
