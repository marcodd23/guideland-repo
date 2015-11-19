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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import it.guideland.app.init.TestDataInitializer;

@Configuration
@Profile("test")
public class JpaTestConfiguration {
	
	Logger logger = LoggerFactory.getLogger(JpaTestConfiguration.class);
	
	
    @Value("${dataSourceEmbedded.driverClassName}")
    private String driver;
    
    @Value("${dataSourceEmbedded.url}")
    private String url;
    
    @Value("${dataSourceEmbedded.username}")
    private String username;
    
    @Value("${dataSourceEmbedded.password}")
    private String password;
    
    @Value("${hibernateEmbedded.dialect}")
    private String dialect;
    
    @Value("${hibernateEmbedded.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    
    @Value("${dataSource.driverClassName}")
    private String jpaVendorAdapterDatabase;
    
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
    
/*   @Bean(name="datasource")
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(driver);
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	return dataSource;
    }*/
	
    @Bean(name="datasource")
    public DataSource dataSource() {
    	logger.debug("============== CREATO IL DATASOURCE HSQL ===================");
    	
    	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    	EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).build();
    	return db;
    }
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) {
		
    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    	entityManagerFactoryBean.setDataSource(datasource);
    	entityManagerFactoryBean.setPackagesToScan(new String[]{"it.guideland.app.model"});
    	entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    	entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
    	Properties jpaProperties = new Properties();
    	jpaProperties.put("hibernate.dialect", dialect);
    	jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.use_sql_comments", "true");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;    	
	}

}
