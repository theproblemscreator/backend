package com.example.UserApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.UserApplication.models.User;
import com.example.UserApplication.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

      // to get all users 
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // save the new user
    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
    	try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //delete the user by id
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/users/{id}")
    public User udateUser(@PathVariable Long id, @RequestBody User user ) {
		return userService.udateUser(id, user);
    	
    }
}