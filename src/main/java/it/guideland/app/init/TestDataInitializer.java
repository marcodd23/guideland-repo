package it.guideland.app.init;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.guideland.app.model.Account;
import it.guideland.app.model.Account.AccountBuilder;
import it.guideland.app.model.City;
import it.guideland.app.model.Guide;
import it.guideland.app.model.GuideInterest;
import it.guideland.app.model.GuideInterestId;
import it.guideland.app.model.Interest;
import it.guideland.app.model.Role;
import it.guideland.app.model.Role.RoleType;
import it.guideland.app.model.SupportedLanguage;
import it.guideland.app.model.User;
import it.guideland.app.model.User.UserBuilder;
import it.guideland.app.repositories.CityRepository;
import it.guideland.app.repositories.GuideRepository;
import it.guideland.app.repositories.InterestRepository;
import it.guideland.app.repositories.RoleRepository;
import it.guideland.app.repositories.SupportedLanguageRepository;
import it.guideland.app.services.RoleService;
import it.guideland.app.services.SupportedLanguageService;
import it.guideland.app.services.UserService;

@Service
public class TestDataInitializer {

	Logger logger = LoggerFactory.getLogger(TestDataInitializer.class);
	
	/*@Autowired
	//private EntityManagerFactory entityManagerFactory;
	private EntityManager em;*/
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SupportedLanguageService supportedLanguageService;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private InterestRepository interestRepo;
	
	@Autowired
	private GuideRepository guideRepo;
	
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
		
		SupportedLanguage supportedLanguage1 = new SupportedLanguage(Locale.ITALIAN.getLanguage(), "Italian");
		SupportedLanguage supportedLanguage2 = new SupportedLanguage(Locale.ENGLISH.getLanguage(), "English");
		
		supportedLanguage1 = supportedLanguageService.persistSuppLanguage(supportedLanguage1);
		supportedLanguage2 = supportedLanguageService.persistSuppLanguage(supportedLanguage2);
		
		List<SupportedLanguage> langs = new ArrayList<>();
		langs.add(supportedLanguage1);
		langs.add(supportedLanguage2);
		

		
		
		UserBuilder userBuilder = new UserBuilder();
		AccountBuilder accountBuilder = new AccountBuilder();
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// ################ Account 1 ####################
		Account account1 = accountBuilder.usernameEmail("username1@prova.it")
				//.password(passwordEncoder.encode("password1"))
				.password("password1")
				.registrationDate(new GregorianCalendar().getTime())
				.enabled(true)
				.notExpired(true)
				.notLocked(true)
				.build();
		
		
		User user1 = userBuilder.account(account1)
			 .bornDate(new GregorianCalendar(1983, GregorianCalendar.APRIL, 23).getTime())
			 .mobileNumber("333-7687999")
			 .name("Marco")
			 //.roles(user1Roles)
			 .sex("M")
			 .usernameEmail(account1.getUsernameEmail())
			 .surname("Di Dionisio")
			 .skype("marco.didionisio")
		     .build();
		
		// ################ Account 2 ####################
		Account account2 = accountBuilder.usernameEmail("username2@prova.it")
				//.password(passwordEncoder.encode("password2"))
				.password("password2")
				.registrationDate(new GregorianCalendar().getTime())
				.enabled(true)
				.notExpired(true)
				.notLocked(true)
				.build();


		User user2 = userBuilder.account(account2)
				.bornDate(new GregorianCalendar(1987, GregorianCalendar.AUGUST, 4).getTime())
				.mobileNumber("333-7693456")
				.name("Daniele")
				//.roles(user2Roles)
				.sex("M")
				.usernameEmail(account2.getUsernameEmail())
				.surname("Simonetti")
				.skype("daniele.simonetti")
				.build();
			
		
		
		roleService.persistRoleAndFlush(adminRole);
		roleService.persistRoleAndFlush(userRole);
		userService.persistUser(user1);
		userService.persistUser(user2);
		
		user1.setRole(userRole);
		user2.setRole(adminRole);

		userService.persistUserAndFlush(user1);
		userService.persistUserAndFlush(user2);
		
		//################################ CITTA' e INTERESSI ###################################
		
		City milan = new City("Milano");
		City madrid = new City("Madrid");
		City hamburg = new City("Hamburg");
		City berlin = new City("Berlin");
		
		milan = cityRepo.save(milan);
		madrid = cityRepo.save(madrid);
		hamburg = cityRepo.save(hamburg);
		berlin = cityRepo.save(berlin);
		
		Interest interest1 = new Interest();
		interest1.setInteresName("interesse 1");
		interest1 = interestRepo.save(interest1);
		Interest interest2 = new Interest();
		interest2.setInteresName("interesse 2");
		interest2 = interestRepo.save(interest2);
		Interest interest3 = new Interest();
		interest3.setInteresName("interesse 3");
		interest3 = interestRepo.save(interest3);
		
		Guide guide1 = new Guide();
		guide1.setCity(milan);
		guide1.setDescription("dfgsdfgsdfgsdfg");
		guide1.setHourlyRate("100");
		guide1.setRegistrationDate(user1.getAccount().getRegistrationDate());
		guide1.setUser(user1);
		
		GuideInterest guideInterest = new GuideInterest();
		guideInterest.setGuide(guide1);
		guideInterest.setInterest(interest1);
		guideInterest.setScore(200);
		
		guide1.getGuideInterests().add(guideInterest);
		
		guideRepo.saveAndFlush(guide1);
		
        //transaction.commit();
	
		

		
	
	}
}
