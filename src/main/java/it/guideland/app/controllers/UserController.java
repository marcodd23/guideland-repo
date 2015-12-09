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
import it.guideland.app.dto.TokenData;
import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.dto.ValidationErrorDTO;
import it.guideland.app.model.Role;
import it.guideland.app.model.User;
import it.guideland.app.repositories.RoleRepository;
import it.guideland.app.repositories.UserRepository;
import it.guideland.app.security.UserRestAuthentication;
import it.guideland.app.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	//private MessageSource messageSource;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	UserValidator validator;
	
	@Autowired 
	ValidatorUtil validatorUtil;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserService UserService;
	
/*	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new UserValidator());
	}*/
	
	@RequestMapping(value = "/private/hello", method = RequestMethod.GET)
	public Object getHome() {
		return ">>>> TUTTO OK!!!! <<<<<<";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication instanceof UserRestAuthentication) {
			TokenData tokenData = (TokenData)((UserRestAuthentication) authentication).getDetails();
			user = userRepo.findUserByUsername(tokenData.getUsername());
		}
		
		//return new User(authentication.getName()); //anonymous user support
		//return new ResponseEntity<User>(user, HttpStatus.OK);
		return user;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserRegistrationDTO userDTO, BindingResult bindingResult, HttpServletRequest request) {
		
		boolean existUsername = userRepo.existAlreadyUsername(userDTO.getUsername());
		boolean existEmail = userRepo.existAlreadyEmail(userDTO.getEmail());

		validator.validate(userDTO, bindingResult, existUsername, existEmail);
		if(bindingResult.hasErrors()){
			ValidationErrorDTO errorDTO = validatorUtil.assembleValidationErrorDTO(bindingResult, request);
			
			return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);
		}else{
			/*UserBuilder userBuilder = new UserBuilder();
			AccountBuilder accountBuilder = new AccountBuilder();
			
			Role role = roleRepo.findByRolename(RoleType.USER.toString());		
			List<Role> userRoles = new ArrayList<>();
			userRoles.add(role);
			//userRoles.add(new Role(RoleType.USER.toString()))
			
			Account account = accountBuilder.username(userDTO.getUsername())
					.email(userDTO.getEmail())
					.password(userDTO.getPassword())
					.registrationDate(new GregorianCalendar().getTime())
					.enabled(true)
					.notExpired(true)
					.notLocked(true)
					.build();
			
			
			User user = userBuilder.account(account)
				 .bornDate(new GregorianCalendar(1983, GregorianCalendar.APRIL, 23).getTime())
				 .email(account.getEmail())
				 .mobileNumber("")
				 .name(userDTO.getName())
				 .roles(userRoles)
				 .sex("")
				 .username(account.getUsername())
				 .surname(userDTO.getSurname())
				 .skype("")
			     .build();
			
			user = userRepo.saveAndFlush(user);*/
			
			User user = UserService.createNewUser(userDTO);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final User user) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = userRepo.findUserByUsername(authentication.getName());

		if (user.getNewPassword() == null || user.getNewPassword().length() < 4) {
			return new ResponseEntity<String>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (!pwEncoder.matches(user.getCurrentPassword(), currentUser.getAccount().getPassword())) {
			return new ResponseEntity<String>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		currentUser.getAccount().setPassword(pwEncoder.encode(user.getNewPassword()));
		userRepo.saveAndFlush(currentUser);
		return new ResponseEntity<String>("password changed", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> grantRole(@PathVariable User user, @PathVariable Role role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.setRole(role);
		userRepo.saveAndFlush(user);
		return new ResponseEntity<String>("role granted", HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<User> list() {
		return userRepo.findAll();
	}

}
