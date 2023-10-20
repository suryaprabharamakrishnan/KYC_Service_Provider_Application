package com.example.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "pan_details", uniqueConstraints = @UniqueConstraint(columnNames = "pan_number"))
public class PanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "pan_number")
	private String panNumber;

	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "father_Name")
	private String fatherName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "pan_user", joinColumns = @JoinColumn(name = "pan_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "email", referencedColumnName = "email"))
	private User user;

	public PanDetails() {

	}

	public PanDetails(String panNumber, String cardHolderName, LocalDate dateOfBirth, String fatherName, User user) {
		super();
		this.panNumber = panNumber;
		this.cardHolderName = cardHolderName;
		this.dateOfBirth = dateOfBirth;
		this.fatherName = fatherName;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
