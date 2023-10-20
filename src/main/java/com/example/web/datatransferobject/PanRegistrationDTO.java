package com.example.web.datatransferobject;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PanRegistrationDTO {

	@Column(name = "pan_number")
	private String panNumber;

	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "father_Name")
	private String fatherName;

	public PanRegistrationDTO(String panNumber, String cardHolderName, LocalDate dateOfBirth, String fatherName) {
		super();
		this.panNumber = panNumber;
		this.cardHolderName = cardHolderName;
		this.dateOfBirth = dateOfBirth;
		this.fatherName = fatherName;
	}

	public PanRegistrationDTO() {

	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

}
