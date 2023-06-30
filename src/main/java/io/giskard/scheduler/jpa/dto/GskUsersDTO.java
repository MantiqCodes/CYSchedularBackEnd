package io.giskard.scheduler.jpa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GskUsersDTO {
	private long                  id;
	@JsonIgnore
	private String                firstName;
	@JsonIgnore
	private String                lastName;
	private String                email;
	@JsonIgnore
	private String                phoneNumber;
	@JsonIgnore
	private int                   isActive;
	@JsonIgnore
	private Date                  dateEntered;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

}
