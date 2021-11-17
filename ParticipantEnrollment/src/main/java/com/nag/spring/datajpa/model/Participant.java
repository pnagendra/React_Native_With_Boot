package com.nag.spring.datajpa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PARTICIPANT_ENROLL")
public class Participant {

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "emailId")
	@Id
	private String emailId;
	
	@Column(name = "dateOfJoining")
	private Date dateOfJoining;
	
	@Column(name = "activationStatus")
	private boolean activationStatus;
	
	
	public Participant() {

	}

	public Participant( String firstName, String lastName, String emailId,Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfJoining = dateOfBirth;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getDateOfBirth() {
		return dateOfJoining;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfJoining = dateOfBirth;
	}
	

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	@Override
	public String toString() {
		return "Participant [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ emailId + ", dateOfBirth=" + dateOfJoining + ", activationStatus=" + activationStatus
				 + "]";
	}



}
