package it.guideland.app.controllers.validators.utils;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import it.guideland.app.dto.ValidationErrorDTO;

@Component
public class ValidatorUtil {

	@Autowired
	private MessageSource messageSource;
	
	public ValidationErrorDTO assembleValidationErrorDTO(BindingResult result, HttpServletRequest request){
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors, request);
		
	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors, HttpServletRequest request) {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError, request);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError, HttpServletRequest request) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		//LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		//Locale currentLocale = localeResolver.resolveLocale(request);
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}		
		return localizedErrorMessage;
	}
}
