package it.guideland.app.services;

import java.util.List;

import it.guideland.app.model.Interest;

public interface InterestService {
	List<Interest> findInterestsByCity(String city);
}
