package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Users;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UserService service;
	// Fetching the All users
	@GetMapping
	public List<Users> getAllUser(){
		return service.getAllUsers(); 
	}
	
	// Fetching the Specific user
	@GetMapping("/{id}")
	public ResponseEntity<Users> getByUserId(@PathVariable Long id){
		return service.getUserById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.noContent().build());
	}
	
	// Saving the User
	@PostMapping("/save")
	public ResponseEntity<Users>saveUser(@RequestBody Users user){
		Users savedPerson = service.SaveUsers(user);
        return ResponseEntity.ok(savedPerson);
	}
	
	// Delete the User
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if(service.getUserById(id).isPresent()) {
			service.deleteUser(id);
		}
		else {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Users> updaateUser(@PathVariable Long id , @RequestBody Users user)
	{
		return service.updateUser(id,user);
	}
}
