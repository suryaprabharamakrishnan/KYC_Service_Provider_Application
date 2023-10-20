package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Entities.User;
import com.example.web.datatransferobject.UserRegistrationDTO;

public interface UserService extends UserDetailsService {
	public User save(UserRegistrationDTO registrationDTO); // method to save user register data

	public boolean loadUserByEmailname(String email);

	public boolean isValidEmail(String email);

	public boolean isValidName(String firstName, String lastName);
}
