package it.guideland.app.init;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.guideland.app.model.Account;
import it.guideland.app.model.Account.AccountBuilder;
import it.guideland.app.model.Role;
import it.guideland.app.model.Role.RoleType;
import it.guideland.app.model.User;
import it.guideland.app.model.User.UserBuilder;
import it.guideland.app.repositories.RoleRepository;
import it.guideland.app.repositories.UserRepository;

@Service
public class TestDataInitializer {

	Logger logger = LoggerFactory.getLogger(TestDataInitializer.class);
	
	/*@Autowired
	//private EntityManagerFactory entityManagerFactory;
	private EntityManager em;*/
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Transactional
	public void init() throws Exception {
		
		logger.debug("============== INIZIALIZZO I DATI DEL DB ===================");
		
		//SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        //Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        
		Role adminRole = new Role(RoleType.ADMIN.toString());
		Role userRole = new Role(RoleType.USER.toString());
		
		List<Role> user1Roles = new ArrayList<>();
		user1Roles.add(adminRole);
		
		List<Role> user2Roles = new ArrayList<>();
		user2Roles.add(userRole);
		
		UserBuilder userBuilder = new UserBuilder();
		AccountBuilder accountBuilder = new AccountBuilder();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// ################ Account 1 ####################
		Account account1 = accountBuilder.username("username-prova-1")
				.email("pippo@sdfs.it")
				.password(passwordEncoder.encode("password1"))
				.registrationDate(new GregorianCalendar().getTime())
				.enabled(true)
				.notExpired(true)
				.notLocked(true)
				.build();
		
		
		User user1 = userBuilder.account(account1)
			 .bornDate(new GregorianCalendar(1983, GregorianCalendar.APRIL, 23).getTime())
			 .email(account1.getEmail())
			 .mobileNumber("333-7687999")
			 .name("Marco")
			 .roles(user1Roles)
			 .sex("M")
			 .username(account1.getUsername())
			 .surname("Di Dionisio")
			 .skype("marco.didionisio")
		     .build();
		
		// ################ Account 2 ####################
		Account account2 = accountBuilder.username("username-prova-2")
				.email("topolino@sdfs.it")
				.password(passwordEncoder.encode("password2"))
				.registrationDate(new GregorianCalendar().getTime())
				.enabled(true)
				.notExpired(true)
				.notLocked(true)
				.build();


		User user2 = userBuilder.account(account2)
				.bornDate(new GregorianCalendar(1987, GregorianCalendar.AUGUST, 4).getTime())
				.email(account2.getEmail())
				.mobileNumber("333-7693456")
				.name("Daniele")
				.roles(user2Roles)
				.sex("M")
				.username(account2.getUsername())
				.surname("Simonetti")
				.skype("daniele.simonetti")
				.build();
								
		
		roleRepo.save(adminRole);
		roleRepo.save(userRole);
		userRepo.save(user1);
		userRepo.save(user2);
		
        //transaction.commit();
	}
}
