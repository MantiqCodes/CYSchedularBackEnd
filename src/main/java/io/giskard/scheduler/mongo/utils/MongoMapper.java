package io.giskard.scheduler.mongo.utils;

import java.util.ArrayList;
import java.util.List;

import io.giskard.scheduler.mongo.dto.AvailabilityDTO;
import io.giskard.scheduler.mongo.dto.ReservationDTO;
import io.giskard.scheduler.mongo.dto.UsersDTO;
import io.giskard.scheduler.mongo.model.Availability;
import io.giskard.scheduler.mongo.model.Reservation;
import io.giskard.scheduler.mongo.model.Users;

public class MongoMapper {

	public static AvailabilityDTO getAvailabilityDTO(Availability avl) {
		AvailabilityDTO dto = new AvailabilityDTO();

		dto.setEndTime(avl.getEndTime());
		dto.setUsersDTO(getUsersDTO(avl.getUsers()));
		dto.setId(avl.getId());
		dto.setIsActive(avl.getIsActive());
		dto.setStartTime(avl.getStartTime());

		return dto;

	}

	public static Availability getAvailability(AvailabilityDTO dto) {

		Availability avl = new Availability();
		avl.setEndTime(dto.getEndTime());
		avl.setUsers(getUsers(dto.getUsersDTO()));
		avl.setId(dto.getId());
		avl.setIsActive(dto.getIsActive());
		avl.setStartTime(dto.getStartTime());

		return avl;

	}

	public static List<AvailabilityDTO> getAvailabilityDTOList(List<Availability> avlList) {
		List<AvailabilityDTO> l = new ArrayList<AvailabilityDTO>();
		for (Availability avl : avlList) {
			l.add(getAvailabilityDTO(avl));
		}
		return l;
	}

	public static List<ReservationDTO> getReservationDTOList(List<Reservation> avlList) {
		List<ReservationDTO> l = new ArrayList<ReservationDTO>();
		for (Reservation avl : avlList) {
			l.add(getReservationDTO(avl));
		}
		return l;
	}

	public static ReservationDTO getReservationDTO(Reservation rsv) {

		ReservationDTO dto = new ReservationDTO();

		dto.setEmail(rsv.getEmail());
		dto.setEndTime(rsv.getEndTime());
		dto.setUsersDTO(getUsersDTO(rsv.getUsers()));
		dto.setId(rsv.getId());
		dto.setStartTime(rsv.getStartTime());
		dto.setTitle(rsv.getTitle());
		dto.setIsActive(rsv.getIsActive());
		dto.setIsComplete(rsv.getIsComplete());
		return dto;
	}

	public static Reservation getReservation(ReservationDTO dto) {

		Reservation rsv = new Reservation();

		rsv.setEmail(dto.getEmail());
		rsv.setEndTime(dto.getEndTime());
		rsv.setUsers(getUsers(dto.getUsersDTO()));
		rsv.setId(dto.getId());
		rsv.setStartTime(dto.getStartTime());
		rsv.setTitle(dto.getTitle());
		rsv.setIsActive(dto.getIsActive());
		rsv.setIsComplete(dto.getIsComplete());
		return rsv;
	}

	public static UsersDTO getUsersDTO(Users u) {
		UsersDTO dto = new UsersDTO();
		dto.setDateEntered(u.getDateEntered());
		dto.setEmail(u.getEmail());
		dto.setFirstName(u.getFirstName());
		dto.setId(u.getId());
		dto.setIsActive(u.getIsActive());
		dto.setLastName(u.getLastName());
		dto.setPhoneNumber(u.getPhoneNumber());
		dto.setReservCount(u.getReservCount());
		dto.setAvailCount(u.getAvailCount());
		dto.setRole(u.getRole());
		
		dto.setPassword(u.getPassword());

		return dto;
	}

	public static Users getUsers(UsersDTO dto) {
		Users u = new Users();
		u.setDateEntered(dto.getDateEntered());
		u.setEmail(dto.getEmail());
		u.setFirstName(dto.getFirstName());
		u.setId(dto.getId());
		u.setIsActive(dto.getIsActive());
		u.setLastName(dto.getLastName());
		u.setPhoneNumber(dto.getPhoneNumber());
		u.setReservCount(dto.getReservCount());
		u.setAvailCount(dto.getAvailCount());
		u.setRole(dto.getRole());
		u.setPassword(dto.getPassword());
		return u;
	}

	public static Users getUsers(Users u) {
		Users uo = new Users();
		uo.setId(u.getId());
		uo.setDateEntered(u.getDateEntered());
		uo.setEmail(u.getEmail());
		uo.setFirstName(u.getFirstName());
		uo.setLastName(u.getLastName());
		uo.setIsActive(u.getIsActive());
		uo.setPhoneNumber(u.getPhoneNumber());
		uo.setReservCount(u.getReservCount());
		uo.setAvailCount(u.getAvailCount());
		uo.setRole(u.getRole());
		uo.setPassword(u.getPassword());
		return uo;

	}

}
