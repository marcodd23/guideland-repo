package it.guideland.app.services;

import java.util.List;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Language;
import it.guideland.app.model.User;

public interface LanguageService {

	List<Language> saveUserLanguagesMap(UserRegistrationDTO userDTO, User user);

}
