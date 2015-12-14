package it.guideland.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Tourist;
import it.guideland.app.model.User;
import it.guideland.app.repositories.TouristRepository;
import it.guideland.app.services.LanguageService;
import it.guideland.app.services.TouristService;
import it.guideland.app.services.UserService;

@Service
public class TouristServiceImpl implements TouristService {

	@Autowired
	UserService userService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	TouristRepository touristRepository;
	
	@Override
	@Transactional
	public Tourist createTourist(UserRegistrationDTO userDTO) {
		
		User user = userService.createNewUser(userDTO);
		
		languageService.saveUserLanguagesMap(userDTO, user);
		
		Tourist tourist = new Tourist();
		
		tourist.setUser(user);
		
		return touristRepository.saveAndFlush(tourist);
		
	}

}
