package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Entities.User;
import com.example.web.datatransferobject.UserRegistrationDTO;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDTO registrationDTO); // method to save user register data

	boolean loadUserByEmailname(String email);

	boolean isValidEmail(String email);
}
