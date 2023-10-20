package com.example.service;

import java.time.LocalDate;

import com.example.Entities.PanDetails;
import com.example.web.datatransferobject.PanRegistrationDTO;

public interface PanService {
	public PanDetails save(PanRegistrationDTO panregistrationDTO); // method to save user register data

	public boolean loadByPanNumber(String panNumber);

	public String deleteByPanNumber(String panNumber);

	public PanDetails viewByPanNumber(String panNumber);

	public PanDetails updatePan(PanDetails panDetails);

	public boolean isValidPanNumber(String panNumber, String cardHolderName);

	public boolean isValidDate(LocalDate dateOfBirth);


}
