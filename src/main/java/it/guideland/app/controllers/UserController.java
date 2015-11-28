package it.guideland.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.guideland.app.dto.TokenData;
import it.guideland.app.model.User;
import it.guideland.app.repositories.UserRepository;
import it.guideland.app.security.UserRestAuthentication;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication instanceof UserRestAuthentication) {
			TokenData tokenData = (TokenData)((UserRestAuthentication) authentication).getDetails();
			user = userRepo.findUserByUsername(tokenData.getUsername());
		}
		//return new User(authentication.getName()); //anonymous user support
		return user;
	}

}
