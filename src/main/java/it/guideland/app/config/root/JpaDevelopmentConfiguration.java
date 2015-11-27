package it.guideland.app.config.root;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import it.guideland.app.init.TestDataInitializer;

@Configuration
@Profile("development")
public class JpaDevelopmentConfiguration{
	
	private Logger logger = LoggerFactory.getLogger(JpaDevelopmentConfiguration.class);
	
    @Value("${dataSource.driverClassName}")
    private String driver;
    
    @Value("${dataSource.url}")
    private String url;
    
    @Value("${dataSource.username}")
    private String username;
    
    @Value("${dataSource.password}")
    private String password;
    
    @Value("${hibernate.dialect}")
    private String dialect;
    
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    
    @Value("${dataSource.driverClassName}")
    private String jpaVendorAdapterDatabase;
    
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
    
    @Bean(name="datasource")
    public DataSource dataSource() {
    	logger.debug("============== CREATO IL DATASOURCE MYSQL ===================");
    	
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(driver);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    }
	
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) {
		
    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    	entityManagerFactoryBean.setDataSource(datasource);
    	entityManagerFactoryBean.setPackagesToScan(new String[]{"it.guideland.app.model"});
    	entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    	entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
    	Properties jpaProperties = new Properties();
    	jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.use_sql_comments", "true");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;    	
	}

}
