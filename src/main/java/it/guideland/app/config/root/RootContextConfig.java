package it.guideland.app.config.root;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.guideland.app.Guideland;

@Configuration
@ComponentScan({ "it.guideland.app.services", "it.guideland.app.security", "it.guideland.app.model",
		"it.guideland.app.dao", "it.guideland.app.init" })
//@ComponentScan(basePackageClasses = Guideland.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
@PropertySource("classpath:persistence.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Guideland.class)
public class RootContextConfig {

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFatory,
			DataSource dataSource) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFatory);
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
