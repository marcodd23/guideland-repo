package it.guideland.app.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.model.User;
import it.guideland.app.repositories.repositoriesCustom.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean existAlreadyUsernameEmail(String usernameEmail){
	    String search = "SELECT u.userId FROM User u WHERE u.usernameEmail = '" + usernameEmail + "'";
	    boolean exist = !em.createQuery(search).getResultList().isEmpty();
	    return exist;
	}

	@Override
	@Transactional
	public User addUser(User user) {
			
		return em.merge(user);
	}

	
}
