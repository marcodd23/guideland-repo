package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.SupportedLanguage;
import it.guideland.app.repositories.repositoriesCustom.SupportedLanguageRepositoryCustom;

public interface SupportedLanguageRepository extends JpaRepository<SupportedLanguage, Long>, SupportedLanguageRepositoryCustom{

}
