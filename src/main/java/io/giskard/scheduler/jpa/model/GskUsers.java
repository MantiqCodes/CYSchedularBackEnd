package io.giskard.scheduler.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "GSK_USERS")
@SequenceGenerator(name = "GSK_USERS_SEQ_GEN", sequenceName = "GSK_USERS_SEQ", allocationSize = 1)
	public class GskUsers {
//	private static final long serialViersionUID = 1L;

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private int isActive;
	private Date dateEntered;
//	private List<GskAvailability> gskAvailabilityList;
//	private List<GskReservation>  gskReservationList;

	public GskUsers() {
		this.dateEntered = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSK_USERS_SEQ")
	@Column(name = "ID")
	public long getId() {

		return this.id;
	}

	public void setId(long id)

	{

		this.id = id;

	}

	@Column(name = "FIRST_NAME")

	public String getFirstName()

	{

		return this.firstName;

	}

	public void setFirstName(String firstName)

	{

		this.firstName = firstName;

	}

	@Column(name = "LAST_NAME")

	public String getLastName()

	{

		return this.lastName;

	}

	public void setLastName(String lastName)

	{

		this.lastName = lastName;

	}

	@Column(name = "EMAIL", unique = true, nullable = false)

	public String getEmail()

	{

		return this.email;

	}

	public void setEmail(String email)

	{

		this.email = email;

	}

	@Column(name = "PHONE")

	public String getPhoneNumber()

	{

		return this.phoneNumber;

	}

	public void setPhoneNumber(String phoneNumber)

	{

		this.phoneNumber = phoneNumber;

	}

	@Column(name = "IS_ACTIVE")

	public int getIsActive()

	{

		return this.isActive;

	}

	public void setIsActive(int isActive)

	{

		this.isActive = isActive;

	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Column(name = "DATE_ENTERED")

	@Temporal(TemporalType.TIMESTAMP)

	public Date getDateEntered()

	{

		return this.dateEntered;

	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	public List<GskAvailability> getGskAvailabilityList()
//	{
//		return this.gskAvailabilityList;
//	}
//
//	public void setGskAvailabilityList(List<GskAvailability> gskAvailabilityList)
//	{
//		this.gskAvailabilityList = gskAvailabilityList;
//	}
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//
//	public List<GskReservation> getGskReservationList()
//	{
//		return gskReservationList;
//	}
//
//	public void setGskReservationList(List<GskReservation> gskReservationList)
//	{
//		this.gskReservationList = gskReservationList;
//	}

	@Override
	public String toString() {
		return "Firstname: " + this.firstName;

	}
}
