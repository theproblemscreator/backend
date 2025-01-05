package com.example.UserApplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.UserApplication.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.example.UserApplication.models.User> user = userRepository.findByUsername(username);
	var userObj = user.get();
	if(user.isPresent()) {
		return User.builder()
	  			.username(userObj.getUsername())
	  			.password(userObj.getPassword())
	  			.roles(userObj.getRole())
	  			.build();
		
	}

		return null;
	}

	

	
	}
