package it.guideland.app.services.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Account;
import it.guideland.app.model.Account.AccountBuilder;
import it.guideland.app.model.Role;
import it.guideland.app.model.Role.RoleType;
import it.guideland.app.model.User;
import it.guideland.app.model.User.UserBuilder;
import it.guideland.app.repositories.UserRepository;
import it.guideland.app.services.AccountService;
import it.guideland.app.services.RoleService;
import it.guideland.app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;
	
	
	@Override
	public User createNewUser(UserRegistrationDTO userDTO) {
		
		UserBuilder userBuilder = new UserBuilder();
		AccountBuilder accountBuilder = new AccountBuilder();
		
		Role role = roleService.findByRolename(RoleType.USER.toString());		
		//List<Role> userRoles = new ArrayList<>();
		//userRoles.add(role);
		//userRoles.add(new Role(RoleType.USER.toString()))
		
		
		Account account = accountBuilder
				.usernameEmail(userDTO.getEmail())
				.password(userDTO.getPassword())
				.registrationDate(new GregorianCalendar().getTime())
				.enabled(true)
				.notExpired(true)
				.notLocked(true)
				.build();
		
		
		User user = userBuilder
			 .account(account)
			 .name(userDTO.getName())
			 .surname(userDTO.getSurname())
			 .usernameEmail(userDTO.getEmail())
			 .bornDate(new GregorianCalendar(1983, GregorianCalendar.APRIL, 23).getTime())
			 .mobileNumber("")
			 .sex("")
			 .skype("")
		     .build();
		
		user.setRole(role);
		
		return persistUser(user);
		//return userRepo.addUser(user);
	}
	
	@Override
	@Transactional
	public User persistUserAndFlush(User user){
		
		return userRepo.saveAndFlush(user);
		
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User persistUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findUserByUsernameEmail(String usernameEmail) {
		return userRepo.findUserByUsernameEmail(usernameEmail);
	}

	@Override
	public boolean existAlreadyUsernameEmail(String usernameEmail) {
		return userRepo.existAlreadyUsernameEmail(usernameEmail);
	}

}
