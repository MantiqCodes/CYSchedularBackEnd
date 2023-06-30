package io.giskard.scheduler.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Document(collection = "Availability")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Availability {

	private long id;
	private Date startTime;
	private Date endTime;
	private int isActive;
	private Users users;

//	private GskReservation gskReservation;
	public Availability() {
		this.isActive = 1;
	}

	@Id
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Field(value = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Field(value = "END_TIME")
	public Date getEndTime() {
		return this.endTime;

	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Field(value = "IS_ACTIVE")
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}
