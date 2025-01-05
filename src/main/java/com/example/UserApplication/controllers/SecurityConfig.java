
package com.example.UserApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.UserApplication.service.MyUserDetailsService;

@Configuration
public class SecurityConfig {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Bean
	public PasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(encodePassword());
		return provider;
	}
	
	@Bean
	  public UserDetailsService userDetailsService() {
		 return myUserDetailsService;
	  }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		return security
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/home","register/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasRole("USER")
						.anyRequest().authenticated()).formLogin(form->form.permitAll()).build();
										
	}
	}
