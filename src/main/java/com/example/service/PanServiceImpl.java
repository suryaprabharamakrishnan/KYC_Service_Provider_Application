package com.example.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Entities.PanDetails;
import com.example.Entities.User;
import com.example.repository.PanRepository;
import com.example.repository.UserRepository;
import com.example.web.datatransferobject.PanRegistrationDTO;

@Service
public class PanServiceImpl implements PanService {

	private UserRepository userRepository;
	private PanRepository panRepository;

	public PanServiceImpl(PanRepository panRepository, UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.panRepository = panRepository;
	}

	@Override
	public boolean isValidPanNumber(String panNumber) {
		String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
		return panNumber.matches(regex);
	}

	@Override
	public PanDetails save(PanRegistrationDTO panregistrationDTO) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		PanDetails pan = new PanDetails(panregistrationDTO.getPanNumber(), panregistrationDTO.getCardHolderName(),
				panregistrationDTO.getDateOfBirth(), panregistrationDTO.getFatherName(), user);
		return panRepository.save(pan);
	}

	@Override
	public boolean loadByPanNumber(String panNumber) {
		PanDetails pan = panRepository.findByPanNumber(panNumber);
		if (pan != null) {
			return true;
		}
		return false;
	}

	@Override
	public String deleteByPanNumber(String panNumber) {
		PanDetails pan = panRepository.findByPanNumber(panNumber);
		panRepository.delete(pan);
		return "success";

	}

	@Override
	public PanDetails viewByPanNumber(String panNumber) {
		PanDetails pan = panRepository.findByPanNumber(panNumber);
		return pan;
	}

	@Override
	public PanDetails updatePan(PanDetails panDetails) {
		return panRepository.save(panDetails);
	}

}
