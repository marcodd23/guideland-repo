package it.guideland.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.guideland.app.controllers.validators.UserValidator;
import it.guideland.app.controllers.validators.utils.ValidatorUtil;
import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.dto.ValidationErrorDTO;
import it.guideland.app.model.City;
import it.guideland.app.model.Interest;
import it.guideland.app.model.Tourist;
import it.guideland.app.model.User;
import it.guideland.app.services.CityService;
import it.guideland.app.services.InterestService;
import it.guideland.app.services.TouristService;
import it.guideland.app.services.UserService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

	@Autowired
	UserValidator validator;

	@Autowired
	ValidatorUtil validatorUtil;

	@Autowired
	UserService userService;

	@Autowired
	TouristService touristService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	InterestService interestService;

	@RequestMapping(value = "/tourist", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> createTouristUser(@RequestBody UserRegistrationDTO userDTO,
			BindingResult bindingResult, HttpServletRequest request) {

		boolean existUsernameEmail = userService.existAlreadyUsernameEmail(userDTO.getEmail());

		validator.validate(userDTO, bindingResult, existUsernameEmail);
		if (bindingResult.hasErrors()) {
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		} else {

			Tourist tourist = touristService.createTourist(userDTO);
			/*
			 * if(tourist != null) { request.setAttribute("X-Username",
			 * userDTO.getEmail()); request.setAttribute("X-Password",
			 * userDTO.getPassword()); return "redirect: /api/login"; }
			 */
			return new ResponseEntity<Object>(tourist.getUser().getUserId(), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/guide", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> createGuideUser(@RequestBody UserRegistrationDTO userDTO, BindingResult bindingResult,
			HttpServletRequest request) {

		boolean existUsernameEmail = userService.existAlreadyUsernameEmail(userDTO.getEmail());

		validator.validate(userDTO, bindingResult, existUsernameEmail);
		if (bindingResult.hasErrors()) {
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		} else {

			Tourist tourist = touristService.createTourist(userDTO);
			return new ResponseEntity<Object>(tourist.getUser().getUserId(), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/guide/wizard/{num}", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> validateUserRegistrationFlow(@RequestBody UserRegistrationDTO userDTO,
			BindingResult bindingResult, HttpServletRequest request, @PathVariable int num) {

		if (num == 1) {
			return guideRegistrationWizardStepOne(userDTO, bindingResult, request);
		} else if (num == 2) {
			return guideRegistrationWizardStepTwo(userDTO, bindingResult, request);
		} else if (num == 3) {

		} else if (num == 4) {

		} else {
			return new ResponseEntity<Object>("{flowStepNum} not correct, should be between [1 - 4]",
					HttpStatus.BAD_REQUEST);
		}
		boolean existUsernameEmail = userService.existAlreadyUsernameEmail(userDTO.getEmail());

		validator.validate(userDTO, bindingResult, existUsernameEmail);
		if (bindingResult.hasErrors()) {
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		} else {

			Tourist tourist = touristService.createTourist(userDTO);
			return new ResponseEntity<Object>(tourist.getUser().getUserId(), HttpStatus.OK);
		}

	}

	private ResponseEntity<Object> guideRegistrationWizardStepOne(UserRegistrationDTO userDTO,
			BindingResult bindingResult, HttpServletRequest request) {
		boolean existUsernameEmail = userService.existAlreadyUsernameEmail(userDTO.getEmail());
		validator.validate(userDTO, bindingResult, existUsernameEmail);
		if (bindingResult.hasErrors()) {
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		} else {
			List<City> cities = cityService.findAllCities();
			return new ResponseEntity<Object>(cities, HttpStatus.OK);
		}
	}

	private ResponseEntity<Object> guideRegistrationWizardStepTwo(UserRegistrationDTO userDTO,
			BindingResult bindingResult, HttpServletRequest request) {
		boolean existCity = false;
		if(userDTO.getCity() != null){
			existCity = cityService.existAlreadyCity(userDTO.getCity());
		}
		if (!existCity) {
			bindingResult.reject("city", "error.city.notexist");
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		} else {
			List<Interest> interests = interestService.findInterestsByCity(userDTO.getCity());
			return new ResponseEntity<Object>(interests, HttpStatus.OK);
		}
	}

	private ResponseEntity<Object> registrationWizardStepThree(UserRegistrationDTO userDTO, BindingResult bindingResult,
			HttpServletRequest request) {
		return null;

	}

	private ResponseEntity<Object> registrationWizardStepFour(UserRegistrationDTO userDTO, BindingResult bindingResult,
			HttpServletRequest request) {
		return null;

	}

}
