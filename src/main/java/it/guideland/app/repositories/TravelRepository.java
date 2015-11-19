package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Travel;
import it.guideland.app.repositories.repositoriesCustom.TravelRepositoryCustom;

public interface TravelRepository extends JpaRepository<Travel, Long>, TravelRepositoryCustom{

}
