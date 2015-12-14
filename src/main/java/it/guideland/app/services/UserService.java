package it.guideland.app.services;

import java.util.List;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.User;

public interface UserService {
	
	public User createNewUser(UserRegistrationDTO userDTO);
	
	public User persistUserAndFlush(User user);
	
	public User persistUser(User user);
	
	public User findUserByUsernameEmail(String usernameEmail);
	
	public boolean existAlreadyUsernameEmail(String usernameEmail);
	
	public List<User> findAllUsers();
}
