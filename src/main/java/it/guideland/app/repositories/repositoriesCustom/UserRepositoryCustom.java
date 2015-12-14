package it.guideland.app.repositories.repositoriesCustom;

import it.guideland.app.model.User;

public interface UserRepositoryCustom {
	
	public boolean existAlreadyUsernameEmail(String email);
	
	public User addUser(User user);
	
}
