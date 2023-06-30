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
@Table(name = "GSK_RESERVATIONS")
@SequenceGenerator(name = "GSK_RESERVATIONS_SEQ_GEN", sequenceName = "GSK_RESERVATIONS_SEQ", allocationSize = 1)

public class GskReservation {

	private long id;
	private Date startTime;
	private Date endTime;
	private String title;
	private String email;
	private int isActive;
	private int isComplete;

//	private int isMissed;

//	private int isCancelled;
	private GskUsers gskUsers;

	public GskReservation() {
		this.isActive = 1;
		this.isComplete = 0;
//		this.isMissed=0;
//		this.isCancelled=0;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSK_RESERVATIONS_SEQ")
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

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_ACTIVE")

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Column(name = "IS_COMPLETE")
	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

//	@Column(name = "IS_MISSED")
//	public int getIsMissed() {
//		return isMissed;
//	}
//
//	public void setIsMissed(int isMissed) {
//		this.isMissed = isMissed;
//	}

//	@Column(name = "IS_CANCELLED")
//	public int getIsCancelled() {
//		return isCancelled;
//	}
//
//	public void setIsCancelled(int isCancelled) {
//		this.isCancelled = isCancelled;
//	}

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
