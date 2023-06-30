package io.giskard.scheduler.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Users")
public class Users {
//	private static final long serialViersionUID = 1L;

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private long reservCount;
	private long availCount;
	private int isActive;
	private Date dateEntered;
	private String role;
	private String password;

	public Users() {
		this.dateEntered = new Date();
		this.isActive = 1;
		this.availCount=0;
		this.reservCount=0;
		this.role="regular";
		this.password="";

	}

	public Users(long id, String firstName, String lastName, String email, String phoneNumber) {
		super();

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateEntered = new Date();
		this.isActive = 1;

	}

	@Id
	@Field(value = "ID")
	public long getId() {

		return this.id;
	}

	public void setId(long id)

	{

		this.id = id;

	}

	@Field(value = "FIRST_NAME")

	public String getFirstName()

	{

		return this.firstName;

	}

	public void setFirstName(String firstName)

	{

		this.firstName = firstName;

	}

	@Field(value = "LAST_NAME")

	public String getLastName()

	{

		return this.lastName;

	}

	public void setLastName(String lastName)

	{

		this.lastName = lastName;

	}

	@Field(value = "EMAIL")

	public String getEmail()

	{

		return this.email;

	}

	public void setEmail(String email)

	{

		this.email = email;

	}

	@Field(value = "PHONE")

	public String getPhoneNumber()

	{

		return this.phoneNumber;

	}

	public void setPhoneNumber(String phoneNumber)

	{

		this.phoneNumber = phoneNumber;

	}

	@Field(value = "RESERV_COUNT")
	public long getReservCount() {
		return reservCount;
	}

	public void setReservCount(long reservCount) {
		this.reservCount = reservCount;
	}

	@Field(value = "AVAIL_COUNT")
	public long getAvailCount() {
		return availCount;
	}

	public void setAvailCount(long availCount) {
		this.availCount = availCount;
	}

	@Field(value = "IS_ACTIVE")

	public int getIsActive()

	{

		return this.isActive;

	}

	public void setIsActive(int isActive)

	{

		this.isActive = isActive;

	}
@Field(value="ROLE")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
@Field(value="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Field(value = "DATE_ENTERED")

	public Date getDateEntered()

	{

		return this.dateEntered;

	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	@Override
	public String toString() {
		return "{" + "Id:" + this.id + ", Firstname: " + this.firstName + ", LastName:" + this.lastName + " , Email:"
				+ this.email + ", Phone: " + this.phoneNumber +", RESERV_COUNT: "+this.reservCount+", Avail_COUNT: "+this.availCount+ ", isActive:" + this.isActive + ", dateEntered: "
				+ this.dateEntered + " }";

	}
}
