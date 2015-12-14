package it.guideland.app.services;

import java.util.List;

import it.guideland.app.model.SupportedLanguage;

public interface SupportedLanguageService {

	List<SupportedLanguage> findAllSupportedLanguages();

	SupportedLanguage persistSuppLanguage(SupportedLanguage supportedLanguage1);

}
