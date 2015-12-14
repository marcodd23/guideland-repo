package it.guideland.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.guideland.app.model.City;
import it.guideland.app.repositories.CityRepository;
import it.guideland.app.services.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepo;
	
	@Override
	public List<City> findAllCities() {
		return cityRepo.findAll();
	}

	@Override
	public boolean existAlreadyCity(String cityName) {
		return cityRepo.existAlreadyCity(cityName);
	}
	
	

}
