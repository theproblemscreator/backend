package com.example.UserApplication.service;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserApplication.models.User;
import com.example.UserApplication.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public void deleteUserById(Long id) {
		repository.deleteById(id);
	}
	
	public User saveUser(User user) {
		 if (repository.existsByEmail(user.getEmail())) {
	            throw new IllegalArgumentException("Email already exists");
	        }
	        return repository.save(user);
	}
	
	
	

}