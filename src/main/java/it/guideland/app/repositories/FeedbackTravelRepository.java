package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.FeedbackTravel;
import it.guideland.app.repositories.repositoriesCustom.FeedbackTravelRepositoryCustom;

public interface FeedbackTravelRepository extends JpaRepository<FeedbackTravel, Long>, FeedbackTravelRepositoryCustom{

}
