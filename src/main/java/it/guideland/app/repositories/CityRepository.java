package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.City;
import it.guideland.app.repositories.repositoriesCustom.CityRepositoryCustom;

public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryCustom{

}
