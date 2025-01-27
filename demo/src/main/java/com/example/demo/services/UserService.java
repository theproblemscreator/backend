package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<Users> getAllUsers() {
		return repository.findAll();
	}

	public Optional<Users> getUserById(Long id) {
		return repository.findById(id);
	}

	public Users SaveUsers(Users user) {
		return repository.save(user);
	}

	public void deleteUser(Long id) {
		repository.deleteById(id);
	}

	public ResponseEntity<Users> updateUser(Long id, Users userData) {

		return repository.findById(id).map(user -> {
			user.setUsername(userData.getUsername());
			user.setEmail(userData.getEmail());
			user.setPassword(userData.getPassword());
			Users updatedPerson = repository.save(user);
			return ResponseEntity.ok(updatedPerson);

		}).orElse(ResponseEntity.notFound().build());
	}
}
