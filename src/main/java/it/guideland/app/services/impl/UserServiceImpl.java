package it.guideland.app.services.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Account;
import it.guideland.app.model.Role;
import it.guideland.app.model.User;
import it.guideland.app.model.Account.AccountBuilder;
import it.guideland.app.model.Role.RoleType;
import it.guideland.app.model.User.UserBuilder;
import it.guideland.app.repositories.RoleRepository;
import it.guideland.app.repositories.UserRepository;
import it.guideland.app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public User createNewUser(UserRegistrationDTO userDTO) {
		
		UserBuilder userBuilder = new UserBuilder();
		AccountBuilder accountBuilder = new AccountBuilder();
		
		Role role = roleRepo.findByRolename(RoleType.USER.toString());		
		//List<Role> userRoles = new ArrayList<>();
		//userRoles.add(role);
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
			 //.roles(userRoles)
			 .sex("")
			 .username(account.getUsername())
			 .surname(userDTO.getSurname())
			 .skype("")
		     .build();
		
		user.setRole(role);
		
		return persistUser(user);
		//return userRepo.addUser(user);
	}
	
	@Transactional
	private User persistUser(User user){
		
		return userRepo.save(user);
		
	}


}
