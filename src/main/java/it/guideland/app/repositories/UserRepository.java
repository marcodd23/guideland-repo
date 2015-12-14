package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.User;
import it.guideland.app.repositories.repositoriesCustom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{

	User findUserByUsernameEmail(String usernameEmail);
}
