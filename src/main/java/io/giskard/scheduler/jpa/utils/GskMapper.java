package io.giskard.scheduler.jpa.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import io.giskard.scheduler.jpa.dto.GskAvailabilityDTO;
import io.giskard.scheduler.jpa.dto.GskReservationDTO;
import io.giskard.scheduler.jpa.dto.GskUsersDTO;
import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.GskReservation;
import io.giskard.scheduler.jpa.model.GskUsers;

public class GskMapper
{

	public static GskAvailabilityDTO getGskAvailabilityDTO(GskAvailability avl)
	{
		GskAvailabilityDTO dto = new GskAvailabilityDTO();

		dto.setEndTime(avl.getEndTime());
		dto.setGskUsersDTO(getGskUsersDTO(avl.getGskUsers()));
		dto.setId(avl.getId());
		dto.setIsActive(avl.getIsActive());
		dto.setStartTime(avl.getStartTime());
		
		return dto;

	}

	public  static GskAvailability getGskAvailability(GskAvailabilityDTO dto)
	{
		

		GskAvailability avl = new GskAvailability();
		avl.setEndTime(dto.getEndTime());
		avl.setGskUsers(getGskUsers(dto.getGskUsersDTO()));
		avl.setId(dto.getId());
		avl.setIsActive(dto.getIsActive());
		avl.setStartTime(dto.getStartTime());
		
		return avl;

	}
	public  static List<GskAvailabilityDTO> getGskAvailabilityDTOList(List<GskAvailability> avlList)
	{
		List<GskAvailabilityDTO> l=new ArrayList<GskAvailabilityDTO>();
		for(GskAvailability avl:avlList)
		{
			l.add(getGskAvailabilityDTO(avl));
		}
		return l;
	}

public  static List<GskReservationDTO> getGskReservationDTOList(List<GskReservation> avlList)
{
	List<GskReservationDTO> l=new ArrayList<GskReservationDTO>();
	for(GskReservation avl:avlList)
	{
		l.add(getGskReservationDTO(avl));
	}
	return l;
}
public  static GskUsersDTO getGskUsersDTO(GskUsers u)
{
	GskUsersDTO dto = new GskUsersDTO();
	dto.setDateEntered(u.getDateEntered());
	dto.setEmail(u.getEmail());
	dto.setFirstName(u.getFirstName());
	dto.setId(u.getId());
	dto.setIsActive(u.getIsActive());
	dto.setLastName(u.getLastName());
	dto.setPhoneNumber(u.getPhoneNumber());
	
	return dto;
}


public  static GskUsers getGskUsers(GskUsersDTO dto)
{
	GskUsers u = new GskUsers();
	u.setDateEntered(dto.getDateEntered());
	u.setEmail(dto.getEmail());
	u.setFirstName(dto.getFirstName());
	u.setId(dto.getId());
	u.setIsActive(dto.getIsActive());
	u.setLastName(dto.getLastName());
	u.setPhoneNumber(dto.getPhoneNumber());
	
	return u;
}


public  static GskReservationDTO getGskReservationDTO(GskReservation rsv)
{
	
	GskReservationDTO dto=new GskReservationDTO();
	
	dto.setEmail(rsv.getEmail());
	dto.setEndTime(rsv.getEndTime());
	dto.setGskUsersDTO(getGskUsersDTO(rsv.getGskUsers()));
	dto.setId(rsv.getId());
	dto.setStartTime(rsv.getStartTime());
	dto.setTitle(rsv.getTitle());
	dto.setIsActive(rsv.getIsActive());
	dto.setIsComplete(rsv.getIsComplete());
	return dto;
}

public static GskUsers getGskUsers(GskUsers u)
{
GskUsers uo=new GskUsers();
uo.setId(u.getId());
uo.setDateEntered(u.getDateEntered());
uo.setEmail(u.getEmail());
uo.setFirstName(u.getFirstName());
uo.setLastName(u.getLastName());
uo.setIsActive(u.getIsActive());
uo.setPhoneNumber(u.getPhoneNumber());
return uo;

}
public  static GskReservation getGskReservation(GskReservationDTO dto)
{
	
	GskReservation rsv=new GskReservation();
	
	rsv.setEmail(dto.getEmail());
	rsv.setEndTime(dto.getEndTime());
	rsv.setGskUsers(getGskUsers(dto.getGskUsersDTO()));
	rsv.setId(dto.getId());
	rsv.setStartTime(dto.getStartTime());
	rsv.setTitle(dto.getTitle());
	rsv.setIsActive(dto.getIsActive());
	rsv.setIsComplete(dto.getIsComplete());
	return rsv;
}
public static Page<GskAvailabilityDTO> getGksAvailabilityDTOPage(Page<GskAvailability> p)
{
	List<GskAvailabilityDTO> dtoList=new ArrayList<GskAvailabilityDTO>();
	for(GskAvailability avl:p)
	{
		dtoList.add(GskMapper.getGskAvailabilityDTO(avl));
	}
	return new PageImpl<GskAvailabilityDTO>(dtoList);
}


public static Page<GskReservationDTO> getGksReservationDTOPage(Page<GskReservation> p)
{
	List<GskReservationDTO> dtoList=new ArrayList<GskReservationDTO>();
	for(GskReservation rsv:p)
	{
		dtoList.add(GskMapper.getGskReservationDTO(rsv));
	}
	return new PageImpl<GskReservationDTO>(dtoList);
}

}
