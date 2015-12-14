package it.guideland.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.model.SupportedLanguage;
import it.guideland.app.repositories.SupportedLanguageRepository;
import it.guideland.app.services.SupportedLanguageService;

@Service
public class SupportedLanguageServiceImpl implements SupportedLanguageService {

	@Autowired
	SupportedLanguageRepository supportedLanguageRepository;
	
	@Override
	public List<SupportedLanguage> findAllSupportedLanguages() {
		return supportedLanguageRepository.findAll();
	}

	@Override
	@Transactional
	public SupportedLanguage persistSuppLanguage(SupportedLanguage supportedLanguage) {
		return supportedLanguageRepository.save(supportedLanguage);
	}

}
