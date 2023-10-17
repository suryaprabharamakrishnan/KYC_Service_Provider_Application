package com.example.service;

import com.example.Entities.PanDetails;
import com.example.web.datatransferobject.PanRegistrationDTO;

public interface PanService {
	PanDetails save(PanRegistrationDTO panregistrationDTO); // method to save user register data

	boolean loadByPanNumber(String panNumber);

	String deleteByPanNumber(String panNumber);

	PanDetails viewByPanNumber(String panNumber);

	PanDetails updatePan(PanDetails panDetails);

	boolean isValidPanNumber(String panNumber);

}
