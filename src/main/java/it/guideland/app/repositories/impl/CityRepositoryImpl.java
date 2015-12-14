package it.guideland.app.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.guideland.app.repositories.repositoriesCustom.CityRepositoryCustom;

public class CityRepositoryImpl implements CityRepositoryCustom {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean existAlreadyCity(String name){
	    String search = "SELECT c.cityId FROM City c WHERE c.name = '" + name + "'";
	    boolean exist = !em.createQuery(search).getResultList().isEmpty();
	    return exist;
	}
}
