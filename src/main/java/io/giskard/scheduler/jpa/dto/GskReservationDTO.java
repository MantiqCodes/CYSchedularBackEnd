package io.giskard.scheduler.jpa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GskReservationDTO {
	private long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	private Date startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	private Date endTime;
	private String title;
	private String email;
	private int isActive;
	private int isComplete;
//	private int isMissed;
//	private int isCancelled;
	private GskUsersDTO gskUsersDTO;

	public GskReservationDTO() {

		this.isActive = 1;
		this.isComplete = 0;
//		this.isMissed = 0;
//		this.isCancelled = 0;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

//	public int getIsMissed() {
//		return isMissed;
//	}
//
//	public void setIsMissed(int isMissed) {
//		this.isMissed = isMissed;
//	}

//	public int getIsCancelled() {
//		return isCancelled;
//	}
//
//	public void setIsCancelled(int isCancelled) {
//		this.isCancelled = isCancelled;
//	}

	public GskUsersDTO getGskUsersDTO() {
		return gskUsersDTO;
	}

	public void setGskUsersDTO(GskUsersDTO gskUsersDTO) {
		this.gskUsersDTO = gskUsersDTO;
	}

}
