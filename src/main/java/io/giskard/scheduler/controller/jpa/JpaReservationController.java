package io.giskard.scheduler.controller.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;

import io.giskard.scheduler.jpa.dto.GskReservationDTO;
import io.giskard.scheduler.jpa.model.GskReservation;
import io.giskard.scheduler.jpa.model.QGskReservation;
import io.giskard.scheduler.jpa.service.GskReservationService;
import io.giskard.scheduler.jpa.utils.GskMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class JpaReservationController {
	@Autowired
	GskReservationService service;
	Logger logger = LoggerFactory.getLogger(JpaReservationController.class);

	@GetMapping(value = "jpaReserv/")
	public ResponseEntity<List<GskReservationDTO>> findAll(UriComponentsBuilder uriComponentsBuilder) {
		List<GskReservation> availList = new ArrayList<>();
		ResponseEntity<List<GskReservationDTO>> responseEntity = new ResponseEntity<List<GskReservationDTO>>(
				GskMapper.getGskReservationDTOList(availList), HttpStatus.NO_CONTENT);
		availList = this.service.findAll();

		if (availList != null && availList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("jpaReserv/").build().toUri());
			responseEntity = new ResponseEntity<List<GskReservationDTO>>(GskMapper.getGskReservationDTOList(availList),
					httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping(value = "jpaReserv/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GskReservationDTO> getById(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) {

		ResponseEntity<GskReservationDTO> responseEnity = new ResponseEntity<GskReservationDTO>(new GskReservationDTO(),
				HttpStatus.BAD_REQUEST);
		Optional<GskReservation> av = this.service.findById(id);
		if (av.isPresent() && av.get().getId() != -1) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("jpaReserv/").buildAndExpand(av.get().getId()).toUri());
			responseEnity = new ResponseEntity<GskReservationDTO>(GskMapper.getGskReservationDTO(av.get()), httpHeaders,
					HttpStatus.OK);
		}
		return responseEnity;

	}

	@GetMapping(value = "jpaReservTime/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GskReservationDTO>> findAvailabilityFromTo(
			@RequestParam(name = "fromSTime", required = true) String fromSTime,
			@RequestParam(name = "toSTime", required = true) String toSTime,
			@RequestParam(name = "email", required = true) String email,

			UriComponentsBuilder uriComponentsBuilder

	) {

		List<GskReservation> avList = new ArrayList<>();
		ResponseEntity<List<GskReservationDTO>> responseEntity = new ResponseEntity<List<GskReservationDTO>>(
				GskMapper.getGskReservationDTOList(avList), HttpStatus.NO_CONTENT);
		if (fromSTime != null && !fromSTime.isEmpty() && toSTime != null && !toSTime.isEmpty() && email != null
		// && CommonUtil.isValidEmail(email)
		) {

			try {
				Date fr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromSTime);
				Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toSTime);
				avList = this.service.findByStartTimeBetween(fr, to, email);

				if (avList != null && avList.size() > 0) {
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(uriComponentsBuilder
							.path("jpaReservTime/?fromSTime=" + fromSTime + "&toSTime=" + toSTime).build().toUri());
					responseEntity = new ResponseEntity<List<GskReservationDTO>>(
							GskMapper.getGskReservationDTOList(avList), httpHeaders, HttpStatus.OK);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return responseEntity;
	}

	@PostMapping(value = "jpaReserv/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GskReservationDTO> create(@RequestBody GskReservationDTO gskRsvDTO,
			UriComponentsBuilder uriComponentsBuilder)

	{
		if (gskRsvDTO != null) {
			GskReservation rv = GskMapper.getGskReservation(gskRsvDTO);
			rv = this.service.save(rv);
			if (this.service.findById(rv.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("jpaReserv/").buildAndExpand(rv.getId()).toUri());
				return new ResponseEntity<GskReservationDTO>(GskMapper.getGskReservationDTO(rv), httpHeaders,
						HttpStatus.OK);
			}
		}
		return null;

	}

	@DeleteMapping("jpaReserv/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id)

	{

		Optional<GskReservation> reservation = this.service.findById(id);
		if (reservation.isPresent() && reservation.get().getId() > -1) {
			this.service.delete(reservation.get().getId());
			if (this.service.findById(reservation.get().getId()).isPresent())
				return ResponseEntity.unprocessableEntity()
						.body("Failed to delte GskReservation with id =" + reservation.get().getId());

		}
		return null;
	}

	@PutMapping(value = "jpaReserv/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody GskReservationDTO avlDTO

	) {

		if (avlDTO != null && avlDTO.getId() > -1) {
			GskReservation avl = GskMapper.getGskReservation(avlDTO);
			Optional<GskReservation> avlNew = this.service.findById(avl.getId());
			if (avlNew.isPresent()) {
				Date stDate = avl.getStartTime();
				Date endDate = avl.getEndTime();
				if (endDate.compareTo(stDate) > 0) {
					GskReservation updatedAvl = this.service.save(avl);
					return new ResponseEntity<Object>(GskMapper.getGskReservationDTO(updatedAvl), HttpStatus.OK);
				}
				// else throw new IllegalArgumentException();
				return ResponseEntity.badRequest().body("End time must be latter than start time");
			}
		}
		return new ResponseEntity<Object>(avlDTO, HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = "jpaSearchRsv/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<GskReservationDTO> search(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@RequestParam(name = "id", required = false) final Long id,
			@RequestParam(name = "userId", required = false) final Long userId,
			@RequestParam(name = "email", required = false) final String email,
			@RequestParam(name = "userEmail", required = false) final String userEmail,
			@RequestParam(name = "title", required = false) final String title,
			@RequestParam(name = "fromStratTime", required = false) final String fromStartTime,
			@RequestParam(name = "toStratTime", required = false) final String toStartTime,
			@RequestParam(name = "fromEndTime", required = false) String fromEndTime,
			@RequestParam(name = "toEndTime", required = false) String toEndTime,
			@RequestParam(name = "reservationId", required = false) final Long reserVationId,
			@RequestParam(name = "isActive", required = false) Integer isActive,
			@RequestParam(name = "isComplete", required = false) Integer isComplete) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (page != null && page < 0)
			page = 0;
		if (size != null && size < 0)
			size = 10;

		if (id != null)
			booleanBuilder.and(QGskReservation.gskReservation.id.eq(id));
		if (userId != null)
			booleanBuilder.and(QGskReservation.gskReservation.gskUsers.id.eq(userId));
		if (userEmail != null && !email.isEmpty())
			booleanBuilder.and(QGskReservation.gskReservation.gskUsers.email.eq(userEmail));
		if (email != null && !email.isEmpty())
			booleanBuilder.and(QGskReservation.gskReservation.email.eq(email));
		if (title != null
		// && !title.isEmpty()
		)
			booleanBuilder.and(QGskReservation.gskReservation.title.eq(title));
		if (isActive != null)
			booleanBuilder.and(QGskReservation.gskReservation.isActive.eq(isActive));
		if (isComplete != null)
			booleanBuilder.and(QGskReservation.gskReservation.isComplete.eq(isComplete));
		try {
			if (fromStartTime != null && toStartTime != null) {
				Date fromSt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromStartTime);
				Date toSt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toStartTime);
				booleanBuilder.and(QGskReservation.gskReservation.startTime.between(fromSt, toSt));
			}
			if (fromEndTime != null && toEndTime != null) {
				Date fromEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromEndTime);
				Date toEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toEndTime);
				booleanBuilder.and(QGskReservation.gskReservation.endTime.between(fromEnd, toEnd));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return GskMapper.getGksReservationDTOPage(
				this.service.findAllPaginated(booleanBuilder.getValue(), page, size, Order.DESC, "id"));
	}

}
