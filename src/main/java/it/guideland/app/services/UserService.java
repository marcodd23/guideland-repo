package it.guideland.app.services;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.User;

public interface UserService {
	public User createNewUser(UserRegistrationDTO userDTO);
}
