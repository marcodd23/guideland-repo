package it.guideland.app.repositories.repositoriesCustom;

import it.guideland.app.model.User;

public interface UserRepositoryCustom {

	public boolean existAlreadyUsername(String username);
	
	public boolean existAlreadyEmail(String email);
	
	public User addUser(User user);
	
}
