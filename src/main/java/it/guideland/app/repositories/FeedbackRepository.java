package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Feedback;
import it.guideland.app.repositories.repositoriesCustom.FeedbackRepositoryCustom;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>, FeedbackRepositoryCustom{

}
