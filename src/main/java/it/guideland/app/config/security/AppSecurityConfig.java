package it.guideland.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.guideland.app.security.RestAuthenticationFilter;
import it.guideland.app.security.RestAuthenticationService;
import it.guideland.app.security.RestLoginFilter;
import it.guideland.app.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private RestAuthenticationService restAuthenticationService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.exceptionHandling()
		.and()
			.anonymous()
		.and()
			.headers().cacheControl()
		.and()
			.authorizeRequests()
			
			//allow anonymous resource requests
			.antMatchers("/").permitAll()
		    
			//allow anonymous POSTs to login
		    .antMatchers(HttpMethod.POST, "/guideland/api/login").permitAll()
		
		    //allow anonymous GETs to API
		    .antMatchers("/guideland/api/**").permitAll()
		    
		    //defined Admin only API area
			.antMatchers("/admin/**").hasRole("ADMIN")
			
			//all other request need to be authenticated
			.anyRequest().hasRole("USER")
			
		.and()
			//Autenticazione -H X-Username: username" -H "X-Password
			.addFilterBefore(new RestLoginFilter("/guideland/api/login", authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)
			
			// custom Token based authentication based on the header previously given to the client
			.addFilterBefore(new RestAuthenticationFilter(restAuthenticationService), UsernamePasswordAuthenticationFilter.class);
		 
			
		/*.and()
		 	.requiresChannel()*/
		    
		
			    
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
}
