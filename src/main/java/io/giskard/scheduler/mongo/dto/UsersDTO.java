package io.giskard.scheduler.mongo.dto;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsersDTO {
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
	private String role;
	private String password ;

	private long reservCount;
	private long availCount;
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
	public long getReservCount() {
		return reservCount;
	}

	public void setReservCount(long reservCount) {
		this.reservCount = reservCount;
	}

	public long getAvailCount() {
		return availCount;
	}

	public void setAvailCount(long availCount) {
		this.availCount = availCount;
	}

	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")

	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}
	@Override
	public String toString() {
		return "UsersDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + ", reservCount=" + reservCount
				+ ", availCount=" + availCount + ", dateEntered=" + dateEntered + "]";
	}

	
}
