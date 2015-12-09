package it.guideland.app.config.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import it.guideland.app.i18n.LocaleResolverImpl;

@Configuration
@EnableWebMvc

@ComponentScan({ "it.guideland.app.controllers", "it.guideland.app.controllers.validators" })
@PropertySource("classpath:application.properties")
public class ServletContextConfig extends WebMvcConfigurerAdapter {

	// To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
	}

	@Bean(name="messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/messages");
		return messageSource;
	}
	
	@Bean(name="localeResolver")
	public LocaleResolver localeResolver() {
		LocaleResolver localeResolver = new LocaleResolverImpl();
		return localeResolver;
	}
	
/*	@Bean(name="localeChangeInterceptor")
	public LocaleChangeInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	@Bean(name="requestMappingHandlerMapping")
	public RequestMappingHandlerMapping requestMappingHandlerMapping(){
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		List<LocaleChangeInterceptor> localeInterceptor = new ArrayList<>();
		localeInterceptor.add(localeChangeInterceptor());
		Object interceptors [] = localeInterceptor.toArray();
		handlerMapping.setInterceptors(interceptors);
		return handlerMapping;
	}*/

}
