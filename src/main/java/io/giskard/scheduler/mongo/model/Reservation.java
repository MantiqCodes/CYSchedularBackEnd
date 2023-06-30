package io.giskard.scheduler.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Reservations")
public class Reservation {

	private long id;
	private Date startTime;
	private Date endTime;
	private String title;
	private String email;
	private int isActive;
	private int isComplete;
	private int isMissed;
	private int isCancelled;
	private Users users;

	public Reservation() {
		this.isActive = 1;
		this.isComplete = 0;
		this.isCancelled=0;
		this.isMissed=0;
	}

	@Id
	@Field(value = "Id")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	public Date getEndTime() {
		return this.endTime;

	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Field(value = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Field(value = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Field(value = "IS_ACTIVE")

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Field(value = "IS_COMPLETE")
	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

	
	@Field(value="IS_MISSED")
	public int getIsMissed() {
		return isMissed;
	}

	public void setIsMissed(int isMissed) {
		this.isMissed = isMissed;
	}
@Field(value="IS_CANCELLED")
	public int getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(int isCancelled) {
		this.isCancelled = isCancelled;
	}

	@JsonIgnore
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
