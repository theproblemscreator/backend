package com.example.UserApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserApplication.models.User;
import com.example.UserApplication.repository.UserRepository;

@Controller
public class Home {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	// This is for Home controller
	@GetMapping("/")
	public String hello() {
		return "home";
	}

	// This is for Admin home controller
	@GetMapping("/admin/home")
	public String admin_home() {
		return "admin_home";
	}

	// This is for User home controller
	@GetMapping("/user/home")
	public String user_home() {
		return "user_home";
	}

	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user); // Save to DB
	}

}
