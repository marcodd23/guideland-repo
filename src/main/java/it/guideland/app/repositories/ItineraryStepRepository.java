package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.ItineraryStep;
import it.guideland.app.repositories.repositoriesCustom.ItineraryStepRepositoryCustom;

public interface ItineraryStepRepository extends JpaRepository<ItineraryStep, Long>, ItineraryStepRepositoryCustom{

}
