package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Tourist;
import it.guideland.app.repositories.repositoriesCustom.TouristRepositoryCustom;

public interface TouristRepository extends JpaRepository<Tourist, Long>, TouristRepositoryCustom{

}
