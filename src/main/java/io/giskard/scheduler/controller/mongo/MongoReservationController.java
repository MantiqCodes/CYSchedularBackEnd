package io.giskard.scheduler.controller.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.giskard.scheduler.mongo.dto.AvailabilityDTO;
import io.giskard.scheduler.mongo.dto.ReservationDTO;
import io.giskard.scheduler.mongo.model.Availability;
import io.giskard.scheduler.mongo.model.Reservation;
import io.giskard.scheduler.mongo.service.MongoReservationService;
import io.giskard.scheduler.mongo.utils.MongoMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class MongoReservationController {
	@Autowired
	MongoReservationService service;
	Logger logger = LoggerFactory.getLogger(MongoReservationController.class);

	@GetMapping(value = "mongoReserv/")
	public ResponseEntity<List<ReservationDTO>> findAll(UriComponentsBuilder uriComponentsBuilder) {
		List<Reservation> availList = new ArrayList<>();
		ResponseEntity<List<ReservationDTO>> responseEntity = new ResponseEntity<List<ReservationDTO>>(
				MongoMapper.getReservationDTOList(availList), HttpStatus.NO_CONTENT);
		availList = this.service.findAll();

		if (availList != null && availList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("mongoReserv/").build().toUri());
			responseEntity = new ResponseEntity<List<ReservationDTO>>(MongoMapper.getReservationDTOList(availList),
					httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping(value = "mongoRsv/")
	public ResponseEntity<List<ReservationDTO>> findAvlByActiveUser(UriComponentsBuilder uriComponentsBuilder,
//			@RequestParam(name = "isActive") Integer isActive,
			@RequestParam(name = "userId") Long userId) {

		List<Reservation> reservList = new ArrayList<>();
		ResponseEntity<List<ReservationDTO>> responseEntity = new ResponseEntity<List<ReservationDTO>>(
				MongoMapper.getReservationDTOList(reservList), HttpStatus.NO_CONTENT);
		if (
//				isActive != null && 
		userId != null)
			reservList = this.service.findByActiveUserId(
//					isActive,
					userId);

		if (reservList != null && reservList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(

//					uriComponentsBuilder.path("mongoReserv/?isActive=" + isActive + "&userId=" + userId).build().toUri());
					uriComponentsBuilder.path("mongoReserv/?userId=" + userId).build().toUri());

			responseEntity = new ResponseEntity<List<ReservationDTO>>(MongoMapper.getReservationDTOList(reservList),
					httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping(value = "mongoReserv/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationDTO> getById(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) {

		ResponseEntity<ReservationDTO> responseEnity = new ResponseEntity<ReservationDTO>(new ReservationDTO(),
				HttpStatus.BAD_REQUEST);
		Optional<Reservation> av = this.service.findById(id);
		if (av.isPresent() && av.get().getId() != -1) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("mongoReserv/").buildAndExpand(av.get().getId()).toUri());
			responseEnity = new ResponseEntity<ReservationDTO>(MongoMapper.getReservationDTO(av.get()), httpHeaders,
					HttpStatus.OK);
		}
		return responseEnity;

	}

	@PostMapping(value = "mongoReserv/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO gskRsvDTO,
			UriComponentsBuilder uriComponentsBuilder)

	{
		if (gskRsvDTO != null) {
			Reservation rv = MongoMapper.getReservation(gskRsvDTO);
			rv = this.service.save(rv);
			if (this.service.findById(rv.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("mongoReserv/").buildAndExpand(rv.getId()).toUri());
				return new ResponseEntity<ReservationDTO>(MongoMapper.getReservationDTO(rv), httpHeaders,
						HttpStatus.OK);
			}
		}
		return null;

	}

	@DeleteMapping("mongoReserv/{id}")
	public void delete(@PathVariable long id)

	{

		Optional<Reservation> reservation = this.service.findById(id);
		if (reservation.isPresent() && reservation.get().getId() > -1) {
			this.service.delete(reservation.get().getId());
		}
	}

	@PutMapping(value = "mongoReserv/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody ReservationDTO avlDTO

	) {

		if (avlDTO != null && avlDTO.getId() > -1) {
			Reservation avl = MongoMapper.getReservation(avlDTO);
			Optional<Reservation> avlNew = this.service.findById(avl.getId());
			if (avlNew.isPresent()) {
				Date stDate = avl.getStartTime();
				Date endDate = avl.getEndTime();
				if (endDate.compareTo(stDate) > 0) {
					Reservation updatedAvl = this.service.save(avl);
					return new ResponseEntity<Object>(MongoMapper.getReservationDTO(updatedAvl), HttpStatus.OK);
				}
				// else throw new IllegalArgumentException();
				return ResponseEntity.badRequest().body("End time must be latter than start time");
			}
		}
		return new ResponseEntity<Object>(avlDTO, HttpStatus.BAD_REQUEST);

	}

}
