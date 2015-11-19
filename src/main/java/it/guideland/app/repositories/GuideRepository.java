package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Guide;
import it.guideland.app.repositories.repositoriesCustom.GuideRepositoryCustom;

public interface GuideRepository extends JpaRepository<Guide, Long>, GuideRepositoryCustom{

}
