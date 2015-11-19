package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Account;
import it.guideland.app.repositories.repositoriesCustom.AccountRepositoryCustom;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom{

}
