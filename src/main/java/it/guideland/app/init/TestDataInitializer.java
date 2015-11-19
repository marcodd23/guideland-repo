package it.guideland.app.init;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataInitializer {

	Logger logger = LoggerFactory.getLogger(TestDataInitializer.class);
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public void init() throws Exception {
		
		logger.debug("============== INIZIALIZZO I DATI DEL DB ===================");
		
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        transaction.commit();
	}
}
