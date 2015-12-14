package it.guideland.app.controllers.validators;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.guideland.app.dto.UserRegistrationDTO;

@Component
public class UserValidator implements Validator {

	private final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/*
	 * ( 
	 * (?=.*\d) # must contains one digit from 0-9
	 * (?=.*[a-z]) # must contains one lowercase characters 
	 * (?=.*[A-Z]) # must contains one uppercase characters 
	 * (?=.*[@#$%]) # must contains one special symbols in the list 
	 * "@#$%" . # match anything with previous condition checking 
	 * {6,20} # length at least 6 characters and maximum of 20 )
	 */
	private final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

	private Pattern patternEmail;
	private Pattern patternPassw;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

	public void validate(Object target, Errors errors, boolean existUsernameEmail) {

		UserRegistrationDTO userDTO = (UserRegistrationDTO) target;
		patternEmail = Pattern.compile(EMAIL_REGEX);
		patternPassw = Pattern.compile(PASSWORD_REGEX);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usernameEmail", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required");

		if (!patternEmail.matcher(userDTO.getEmail()).matches()) {
			errors.rejectValue("email", "errors.email");
		}

		if (!patternPassw.matcher(userDTO.getPassword()).matches()) {
			errors.rejectValue("password", "errors.email");
		}

		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
			errors.rejectValue("password", "errors.notmatch.password");
		}
		if (!userDTO.getEmail().equals(userDTO.getConfirmEmail())) {
			errors.rejectValue("email", "errors.notmatch.email");
		}
		if (existUsernameEmail) {
			errors.rejectValue("username", "errors.username.already.exist");
		}
	}

}
