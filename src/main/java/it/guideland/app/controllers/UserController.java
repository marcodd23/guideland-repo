package it.guideland.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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
import it.guideland.app.model.Role;
import it.guideland.app.model.Tourist;
import it.guideland.app.model.User;
import it.guideland.app.security.TokenData;
import it.guideland.app.security.UserRestAuthentication;
import it.guideland.app.services.TouristService;
import it.guideland.app.services.UserService;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

	@Autowired
	UserValidator validator;

	@Autowired
	ValidatorUtil validatorUtil;

	@Autowired
	UserService userService;

	@Autowired
	TouristService touristService;

	/*
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.setValidator(new UserValidator()); }
	 */

	@RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication instanceof UserRestAuthentication) {
			TokenData tokenData = (TokenData) ((UserRestAuthentication) authentication).getDetails();
			user = userService.findUserByUsernameEmail(tokenData.getUsernameEmail());
		}
		// return new User(authentication.getName()); //anonymous user support
		// return new ResponseEntity<User>(user, HttpStatus.OK);
		return user;
	}

/*	@RequestMapping(value = "/tourist/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
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
			return new ResponseEntity<Object>(tourist.getUser().getUserId(), HttpStatus.OK);
		}

	}*/

/*	@RequestMapping(value = "/guide/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
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

	}*/

	@RequestMapping(value = "/guide/validate/{flowStepNum}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> validateUserRegistrationFlow(@RequestBody UserRegistrationDTO userDTO,
			BindingResult bindingResult, HttpServletRequest request, @PathVariable int flowStepNum) {

		if(flowStepNum==1){
			
		}else if(flowStepNum==2){
			
		}else if(flowStepNum==3){
			
		}else{
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
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

	@RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final User user) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = userService.findUserByUsernameEmail(authentication.getName());

		if (user.getNewPassword() == null || user.getNewPassword().length() < 4) {
			return new ResponseEntity<String>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (!pwEncoder.matches(user.getCurrentPassword(), currentUser.getAccount().getPassword())) {
			return new ResponseEntity<String>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		currentUser.getAccount().setPassword(pwEncoder.encode(user.getNewPassword()));
		userService.persistUser(currentUser);
		return new ResponseEntity<String>("password changed", HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}",
	 * method = RequestMethod.POST) public ResponseEntity<String>
	 * grantRole(@PathVariable User user, @PathVariable Role role) { if (user ==
	 * null) { return new ResponseEntity<String>("invalid user id",
	 * HttpStatus.UNPROCESSABLE_ENTITY); }
	 * 
	 * user.setRole(role); userService.persistUser(user); return new
	 * ResponseEntity<String>("role granted", HttpStatus.OK); }
	 */

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<User> list() {
		return userService.findAllUsers();
	}

}
