package it.guideland.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.guideland.app.model.Interest;
import it.guideland.app.repositories.InterestRepository;
import it.guideland.app.services.InterestService;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	InterestRepository interestRepo;
	
	@Override
	public List<Interest> findInterestsByCity(String city) {
		return interestRepo.findInterestsByCity(city);
	}

}
