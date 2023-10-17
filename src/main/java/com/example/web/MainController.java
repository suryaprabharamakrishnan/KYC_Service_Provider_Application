package com.example.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Entities.User;
import com.example.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		String userEmail = principal.getName();
		User user = userRepository.findByEmail(userEmail);
		model.addAttribute("user", user);
		return "index";
	}

}
