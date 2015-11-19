package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Request;
import it.guideland.app.repositories.repositoriesCustom.RequestRepositoryCustom;

public interface RequestRepository extends JpaRepository<Request, Long>, RequestRepositoryCustom{

}
