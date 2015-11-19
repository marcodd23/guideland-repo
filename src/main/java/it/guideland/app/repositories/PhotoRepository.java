package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Photo;
import it.guideland.app.repositories.repositoriesCustom.PhotoRepositoryCustom;

public interface PhotoRepository extends JpaRepository<Photo, Long>, PhotoRepositoryCustom{

}
