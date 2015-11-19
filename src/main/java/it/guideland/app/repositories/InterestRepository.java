package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Interest;
import it.guideland.app.repositories.repositoriesCustom.InterestRepositoryCustom;

public interface InterestRepository extends JpaRepository<Interest, Long>, InterestRepositoryCustom{

}
