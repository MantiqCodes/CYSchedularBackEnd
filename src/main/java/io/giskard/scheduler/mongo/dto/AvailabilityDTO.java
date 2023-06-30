package io.giskard.scheduler.mongo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
public class AvailabilityDTO {
	private long    id;
	private Date    startTime;
	private Date    endTime;
	private int     isActive;
	private UsersDTO usersDTO;
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
	public UsersDTO getUsersDTO()
	{
		return usersDTO;
	}
	public void setUsersDTO(UsersDTO usersDTO)
	{
		this.usersDTO = usersDTO;
	}
	@Override
	public String toString() {
		return "AvailabilityDTO [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", isActive="
				+ isActive + ", usersDTO=" + usersDTO + "]";
	}
	
}
