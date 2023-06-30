package io.giskard.scheduler.jpa.dto;

import java.util.List;

public class ReservationResponseDTO {
private GskUsersDTO gskUsersDTO;
private List<GskReservationDTO>reservationList;
public GskUsersDTO getGskUsersDTO() {
	return gskUsersDTO;
}
public void setGskUsersDTO(GskUsersDTO gskUsersDTO) {
	this.gskUsersDTO = gskUsersDTO;
}
public List<GskReservationDTO> getReservationList() {
	return reservationList;
}
public void setReservationList(List<GskReservationDTO> reservationList) {
	this.reservationList = reservationList;
}

}
