package it.guideland.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.guideland.app.dto.TokenData;
import it.guideland.app.model.Role;
import it.guideland.app.model.User;
import it.guideland.app.repositories.UserRepository;
import it.guideland.app.security.UserRestAuthentication;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/private/hello", method = RequestMethod.GET)
	public Object getHome() {
		return ">>>> TUTTO OK!!!! <<<<<<";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<User> getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication instanceof UserRestAuthentication) {
			TokenData tokenData = (TokenData)((UserRestAuthentication) authentication).getDetails();
			user = userRepo.findUserByUsername(tokenData.getUsername());
		}
		//return new User(authentication.getName()); //anonymous user support
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final User user) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = userRepo.findUserByUsername(authentication.getName());

		if (user.getNewPassword() == null || user.getNewPassword().length() < 4) {
			return new ResponseEntity<String>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (!pwEncoder.matches(user.getCurrentPassword(), currentUser.getAccount().getPassword())) {
			return new ResponseEntity<String>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		currentUser.getAccount().setPassword(pwEncoder.encode(user.getNewPassword()));
		userRepo.saveAndFlush(currentUser);
		return new ResponseEntity<String>("password changed", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> grantRole(@PathVariable User user, @PathVariable Role role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.grantRole(role);
		userRepo.saveAndFlush(user);
		return new ResponseEntity<String>("role granted", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/api/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> revokeRole(@PathVariable User user, @PathVariable Role role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.revokeRole(role);
		userRepo.saveAndFlush(user);
		return new ResponseEntity<String>("role revoked", HttpStatus.OK);
	}
	

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<User> list() {
		return userRepo.findAll();
	}

}
