package com.example.UserApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UserApplication.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	boolean existsByEmail(String email);
}
