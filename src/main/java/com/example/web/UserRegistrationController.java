package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.UserService;
import com.example.web.datatransferobject.UserRegistrationDTO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDTO registrationDTO() {
		return new UserRegistrationDTO();
	}

	// method handler to redirect to HTML Front-End page
	@GetMapping
	public String showRegistrationForm() {
		return "registration"; // return the name of the HTML file for registration page
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
		if (!userService.isValidName(registrationDTO.getFirstName(), registrationDTO.getLastName())) {
			return "redirect:/registration?nameformaterror";
		}
		
		if (!userService.isValidEmail(registrationDTO.getEmail())) {
			return "redirect:/registration?emailformaterror";
		}
		
		if (userService.loadUserByEmailname(registrationDTO.getEmail())) {
			return "redirect:/registration?emailerror";

		} else {
			userService.save(registrationDTO);
			return "redirect:/registration?success";

		}

	}
}
