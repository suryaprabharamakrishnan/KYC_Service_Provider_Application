package com.example.web;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entities.PanDetails;
import com.example.Entities.User;
import com.example.repository.PanUserRepository;
import com.example.repository.UserRepository;
import com.example.service.PanService;
import com.example.web.datatransferobject.PanRegistrationDTO;

@Controller
@RequestMapping
public class PanRegistrationController {

	private PanService panService;
	private PanUserRepository panUserRepository;
	private UserRepository userRepository;

	public PanRegistrationController(PanService panService, PanUserRepository panUserRepository,
			UserRepository userRepository) {
		super();
		this.panService = panService;
		this.panUserRepository = panUserRepository;
		this.userRepository = userRepository;
	}

	@ModelAttribute("pan")
	public PanRegistrationDTO pregistrationDTO() {
		return new PanRegistrationDTO();
	}

	@GetMapping("/KYCcreation")
	public String showcreateKYCForm() {
		return "create";
	}

	@PostMapping("/KYCcreation")
	public String registerPanCard(@ModelAttribute("pan") PanRegistrationDTO panregistrationDTO, Principal principal) {
		if (!panService.isValidPanNumber(panregistrationDTO.getPanNumber())) {
			return "redirect:/KYCcreation?panNumberValidationError";
		}
		if (panService.loadByPanNumber(panregistrationDTO.getPanNumber())) {
			return "redirect:/KYCcreation?panerror";

		} else {
			if (panUserRepository.existsByEmail(principal.getName())) {
				return "redirect:/KYCcreation?pancreationerror";
			}
			panService.save(panregistrationDTO);
			return "redirect:/KYCcreation?success";
		}
	}

	@GetMapping("/KYCupdation")
	public String showupdateKYCForm() {
		return "update";
	}

	@PostMapping("/KYCupdation")
	public String updatePanCard(@ModelAttribute("pan") PanRegistrationDTO panRegistrationDTO) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		String panNumber = panRegistrationDTO.getPanNumber();
		PanDetails panDetails = panService.viewByPanNumber(panNumber);

		if (panDetails != null && panDetails.getUser() == user) {
			panDetails.setCardHolderName(panRegistrationDTO.getCardHolderName());
			panDetails.setDateOfBirth(panRegistrationDTO.getDateOfBirth());
			panDetails.setFatherName(panRegistrationDTO.getFatherName());
			panService.updatePan(panDetails);
			return "redirect:/KYCupdation?success";
		} else {

			return "redirect:/KYCupdation?panerror";
		}
	}

	@GetMapping("/KYCview")
	public String showviewKYCForm() {
		return "view";
	}

	@PostMapping("/KYCview")
	public String viewPanCard(@ModelAttribute("pan") PanRegistrationDTO panregistrationDTO, Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		PanDetails viewPan = panService.viewByPanNumber(panregistrationDTO.getPanNumber());
		if (viewPan != null && viewPan.getUser() == user) {
			model.addAttribute("panCard", viewPan);
			return "view";
		}
		return "redirect:/KYCview?panAccessError";
	}

	@GetMapping("/KYCdeletion")
	public String showdeleteKYCForm() {
		return "delete";
	}

	@PostMapping("/KYCdeletion")
	public String deletePanCard(@ModelAttribute("pan") PanRegistrationDTO panregistrationDTO) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		PanDetails pan = panService.viewByPanNumber(panregistrationDTO.getPanNumber());
		if (pan.getUser() == user) {
			panService.deleteByPanNumber(pan.getPanNumber());
			return "redirect:/KYCdeletion?success";
		}
		return "redirect:/KYCdeletion?panerror";
	}

	@GetMapping("/panVerification")
	public String showverifyKYCForm() {
		return "verify";
	}

	@PostMapping("/panVerification")
	public String verifyPanCard(@ModelAttribute PanRegistrationDTO panRegistrationDTO) {
		String panNumber = panRegistrationDTO.getPanNumber();
		PanDetails panDetails = panService.viewByPanNumber(panNumber);

		if (panService.loadByPanNumber(panRegistrationDTO.getPanNumber())) {
			if (panDetails.getCardHolderName().equals(panRegistrationDTO.getCardHolderName())
					&& panDetails.getDateOfBirth().equals(panRegistrationDTO.getDateOfBirth())
					&& panDetails.getFatherName().equals(panRegistrationDTO.getFatherName())) {
				return "redirect:/panVerification?success";
			} else {
				return "redirect:/panVerification?panerror";
			}

		} else {

			return "redirect:/panVerification?panerror";
		}

	}
}
