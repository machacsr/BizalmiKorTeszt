package com.teszt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.teszt.service.UserServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider());
	}
	 
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
		.authorizeRequests()
			.antMatchers("/console/**").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/reg").permitAll()
			.antMatchers("/css/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	
}
