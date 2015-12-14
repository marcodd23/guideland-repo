package it.guideland.app.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.guideland.app.model.City;
import it.guideland.app.model.Interest;
import it.guideland.app.repositories.CityRepository;
import it.guideland.app.repositories.repositoriesCustom.InterestRepositoryCustom;

public class InterestRepositoryImpl implements InterestRepositoryCustom {

	@Autowired
	private CityRepository cityRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Interest> findInterestsByCity(String name) {
		 String search = "SELECT c.cityId FROM City c WHERE c.name = '" + name + "'";
		    List<City> cityResult = (List<City>) em.createQuery(search).getResultList();
		    if(!cityResult.isEmpty()){
		    	return cityResult.get(0).getInterests();	
		    }else {
		    	return null;
		    }
	}

}
