package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Itinerary;
import it.guideland.app.repositories.repositoriesCustom.ItineraryStepRepositoryCustom;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long>, ItineraryStepRepositoryCustom{

}
