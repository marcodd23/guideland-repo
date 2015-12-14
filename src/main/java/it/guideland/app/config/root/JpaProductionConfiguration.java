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
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import it.guideland.app.init.TestDataInitializer;

@Configuration
@Profile("production")
public class JpaProductionConfiguration {
private Logger logger = LoggerFactory.getLogger(JpaProductionConfiguration.class);
	
    @Value("${dataSource.jndiName}")
    private String jndiName;
    
    
    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }
   
    @Bean(name="datasource")
    public JndiObjectFactoryBean dataSource(){
    	JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
    	jndiObjectFB.setJndiName(jndiName);
    	jndiObjectFB.setResourceRef(true);
    	jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
    	return jndiObjectFB;
    }
	
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) {
		
    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    	if(datasource != null){
    		logger.debug(">>>>>>>>> DATASOURCE PRODUCTION EXIST <<<<<<<<<<");
    	}else{
    		logger.debug(">>>>>>>>> DATASOURCE PRODUCTION NOT EXIST <<<<<<<<<<");
    	}
    	
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
