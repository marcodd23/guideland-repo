package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Language;
import it.guideland.app.repositories.repositoriesCustom.LanguageRepositoryCustom;

public interface LanguageRepository extends JpaRepository<Language, Long>, LanguageRepositoryCustom{

}
