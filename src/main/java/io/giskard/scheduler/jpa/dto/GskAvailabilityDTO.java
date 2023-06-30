package io.giskard.scheduler.jpa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
public class GskAvailabilityDTO {
	private long    id;
	private Date    startTime;
	private Date    endTime;
	private int     isActive;
	private GskUsersDTO gskUsersDTO;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")

	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public GskUsersDTO getGskUsersDTO()
	{
		return gskUsersDTO;
	}
	public void setGskUsersDTO(GskUsersDTO gskUsersDTO)
	{
		this.gskUsersDTO = gskUsersDTO;
	}
	

}
