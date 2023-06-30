package io.giskard.scheduler.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GSK_AVAILABLITLIY")
@SequenceGenerator(name = "GSK_AVAILABILITY_SEQ_GEN", sequenceName = "GSK_AVAILABILITY_SEQ", allocationSize = 1)

public class GskAvailability {

	private long id;
	private Date startTime;
	private Date endTime;
	private int isActive;
	private GskUsers gskUsers;

//	private GskReservation gskReservation;
	public GskAvailability() {
		this.isActive = 1;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSK_AVAILABILITY_SEQ")
	@Column(name = "ID")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Column(name = "START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Column(name = "END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return this.endTime;

	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "IS_ACTIVE")
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	@JsonIgnore
	public GskUsers getGskUsers() {
		return this.gskUsers;
	}

	public void setGskUsers(GskUsers gskusers) {
		this.gskUsers = gskusers;
	}
}
