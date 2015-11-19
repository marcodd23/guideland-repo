package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.LoginAttempt;
import it.guideland.app.repositories.repositoriesCustom.LoginAttemptRepositoryCustom;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long>, LoginAttemptRepositoryCustom{

}
