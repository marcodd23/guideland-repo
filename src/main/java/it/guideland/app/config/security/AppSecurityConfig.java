package it.guideland.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.guideland.app.security.RestAuthenticationEntryPoint;
import it.guideland.app.security.RestAuthenticationFailureHandler;
import it.guideland.app.security.RestAuthenticationFilter;
import it.guideland.app.security.RestAuthenticationService;
import it.guideland.app.security.RestAuthenticationSuccessHandler;
import it.guideland.app.security.RestLoginFilter;
import it.guideland.app.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationService restAuthenticationService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and().csrf().disable()
				.formLogin().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().and().anonymous().and().headers().cacheControl().and().authorizeRequests()

				.antMatchers("/private/hello").hasRole("USER")
				// .antMatchers("/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()

				// allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()

				.antMatchers(HttpMethod.GET, "/api/login").denyAll()

				// allow anonymous GETs to API
				.antMatchers("/api/**").authenticated()

				// defined Admin only API area
				.antMatchers("/admin/**").hasRole("ADMIN")

				// all other request need to be authenticated
				.anyRequest().hasRole("USER")

				.and()
				// Autenticazione -H X-Username: username" -H "X-Password
				.addFilterBefore(
						new RestLoginFilter("/api/login", authenticationManagerBean(),
								restAuthenticationFailureHandlerBean(), RestAuthenticationSuccessHandlerBean(),
								restAuthenticationService, userDetailsServiceImplBean()),
						UsernamePasswordAuthenticationFilter.class)

				// custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(new RestAuthenticationFilter(restAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);

		/*
		 * .and() .requiresChannel()
		 */

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean(name = "restAuthenticationSuccessHandler")
	public RestAuthenticationSuccessHandler RestAuthenticationSuccessHandlerBean() {
		return new RestAuthenticationSuccessHandler();
	}

	@Bean(name = "restAuthenticationFailureHandler")
	public RestAuthenticationFailureHandler restAuthenticationFailureHandlerBean() {
		return new RestAuthenticationFailureHandler();
	}

	@Bean(name = "userDetailsServiceImpl")
	public UserDetailsServiceImpl userDetailsServiceImplBean() {
		return new UserDetailsServiceImpl();
	}

}
