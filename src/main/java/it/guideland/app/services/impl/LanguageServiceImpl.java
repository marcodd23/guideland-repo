package it.guideland.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Language;
import it.guideland.app.model.User;
import it.guideland.app.repositories.LanguageRepository;
import it.guideland.app.services.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;
	
	@Override
	public List<Language> saveUserLanguagesMap(UserRegistrationDTO userDTO, User user) {
		// TODO Auto-generated method stub
		
		Map<String, String> languagesMap = userDTO.getLanguages();
		List<Language> languagesList = new ArrayList<>();
		for (Map.Entry<String, String> entry : languagesMap.entrySet()) {
			Language lang = new Language();
			lang.setLanguage(entry.getKey());
			lang.setLevel(entry.getValue());
			lang.setUser(user);
			languagesList.add(lang);
		}
		
		return persistUserLanguages(languagesList);
	}
	
	@Transactional
	private List<Language> persistUserLanguages(List<Language> languages){
		return languageRepository.save(languages);
	}

}
