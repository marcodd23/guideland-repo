package it.guideland.app.services;

import java.util.List;

import it.guideland.app.model.City;

public interface CityService {

	public List<City> findAllCities();
	
	public boolean existAlreadyCity(String cityName);
}
