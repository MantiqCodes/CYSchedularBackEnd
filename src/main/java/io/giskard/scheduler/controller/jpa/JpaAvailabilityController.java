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

import io.giskard.scheduler.jpa.dto.GskAvailabilityDTO;
import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.QGskAvailability;
import io.giskard.scheduler.jpa.service.GskAvailabilityService;
import io.giskard.scheduler.jpa.utils.GskMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class JpaAvailabilityController {

	@Autowired
	GskAvailabilityService service;

	Logger logger = LoggerFactory.getLogger(JpaAvailabilityController.class);

	@GetMapping(value ="jpaAvail/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GskAvailabilityDTO> getGskAvailabilityById(@PathVariable Long id,
			UriComponentsBuilder uriComponentsBuilder) {

		ResponseEntity<GskAvailabilityDTO> responseEnity = new ResponseEntity<GskAvailabilityDTO>(
				new GskAvailabilityDTO(), HttpStatus.BAD_REQUEST);
		Optional<GskAvailability> av = this.service.findById(id);
		if (av.isPresent() && av.get().getId() != -1) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("jpaAvail/").buildAndExpand(av.get().getId()).toUri());
			responseEnity = new ResponseEntity<GskAvailabilityDTO>(GskMapper.getGskAvailabilityDTO(av.get()),
					httpHeaders, HttpStatus.OK);
		}
		return responseEnity;

	}

	@GetMapping(value ="jpaAvail/")
	public ResponseEntity<List<GskAvailabilityDTO>> findAllAvailability(UriComponentsBuilder uriComponentsBuilder) {

		List<GskAvailability> availList = new ArrayList<>();
		ResponseEntity<List<GskAvailabilityDTO>> responseEntity = new ResponseEntity<List<GskAvailabilityDTO>>(
				GskMapper.getGskAvailabilityDTOList(availList), HttpStatus.NO_CONTENT);
		availList = this.service.findAll();

		if (availList != null && availList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("jpaAvail/").build().toUri());
			responseEntity = new ResponseEntity<List<GskAvailabilityDTO>>(
					GskMapper.getGskAvailabilityDTOList(availList), httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping(value ="jpaAvailTime/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GskAvailabilityDTO>> findAvailabilityFromTo(
			@RequestParam(name = "fromSTime", required = true) String fromSTime,
			@RequestParam(name = "toSTime", required = true) String toSTime,
			@RequestParam(name = "email", required = true) String email, UriComponentsBuilder uriComponentsBuilder) {
		List<GskAvailability> avList = new ArrayList<>();
		ResponseEntity<List<GskAvailabilityDTO>> responseEntity = new ResponseEntity<List<GskAvailabilityDTO>>(
				GskMapper.getGskAvailabilityDTOList(avList), HttpStatus.NO_CONTENT);
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
							.path("jpaAvailTime/?fromSTime=" + fromSTime + "&toSTime=" + toSTime).build().toUri());
					responseEntity = new ResponseEntity<List<GskAvailabilityDTO>>(
							GskMapper.getGskAvailabilityDTOList(avList), httpHeaders, HttpStatus.OK);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return responseEntity;
	}

	@PostMapping(value ="jpaAvail/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GskAvailabilityDTO> createGskAvailability(@RequestBody GskAvailabilityDTO gskSrvDTO,
			UriComponentsBuilder uriComponentsBuilder)

	{
		if (gskSrvDTO != null) {
			GskAvailability av = GskMapper.getGskAvailability(gskSrvDTO);
			av = this.service.saveAvailability(av);
			if (this.service.findById(av.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("jpaAvail/").buildAndExpand(av.getId()).toUri());
				return new ResponseEntity<GskAvailabilityDTO>(GskMapper.getGskAvailabilityDTO(av), httpHeaders,
						HttpStatus.OK);
			}
		}
		return null;

	}

	@DeleteMapping("jpaAvail/{id}")
	public ResponseEntity<Object> deleteGskAvailability(@PathVariable long id)

	{

		Optional<GskAvailability> availability = this.service.findById(id);
		if (availability.isPresent() && availability.get().getId() > -1) {
			this.service.delete(availability.get().getId());
			if (this.service.findById(availability.get().getId()).isPresent())
				return ResponseEntity.unprocessableEntity()
						.body("Failed to delte Availability with id =" + availability.get().getId());

		}
		return null;
	}

	@PutMapping(value ="jpaAvail/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody GskAvailabilityDTO avlDTO

	) {
		
			if (avlDTO != null && avlDTO.getId() > -1) {
				GskAvailability avl = GskMapper.getGskAvailability(avlDTO);

				Optional<GskAvailability> avlNew = this.service.findById(avl.getId());
				if (avlNew.isPresent()) {
					Date stDate = avl.getStartTime();
					Date endDate = avl.getEndTime();
					if (endDate.compareTo(stDate) > 0) {
						GskAvailability updatedAvl = this.service.saveAvailability(avl);
						return new ResponseEntity<Object>(GskMapper.getGskAvailabilityDTO(updatedAvl), HttpStatus.OK);
					}

					return ResponseEntity.badRequest().body("End time must be latter than start time");
				}
			}
		
		return new ResponseEntity<Object>(avlDTO, HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = "jpaSearchAvl/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<GskAvailabilityDTO> searchAvailablility(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "100") Integer size,
			@RequestParam(name = "id", required = false) final Long id,
			@RequestParam(name = "userId", required = false) final Long userId,
			@RequestParam(name = "email", required = false) final String email,
			@RequestParam(name = "fromStratTime", required = false) final String fromStartTime,
			@RequestParam(name = "toStratTime", required = false) final String toStartTime,
			@RequestParam(name = "fromEndTime", required = false) String fromEndTime,
			@RequestParam(name = "toEndTime", required = false) String toEndTime,
			@RequestParam(name = "reservationId", required = false) final Long reserVationId,
			@RequestParam(name = "isActive", required = false) Integer isActive) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (page != null && page < 0)
			page = 0;
		if (size != null && size < 0)
			size = 10;

		if (id != null)
			booleanBuilder.and(QGskAvailability.gskAvailability.id.eq(id));
		if (userId != null)
			booleanBuilder.and(QGskAvailability.gskAvailability.gskUsers.id.eq(userId));
		if (email != null && !email.isEmpty())
			booleanBuilder.and(QGskAvailability.gskAvailability.gskUsers.email.eq(email));
		if (isActive != null)
			booleanBuilder.and(QGskAvailability.gskAvailability.isActive.eq(isActive));
		try {
			if (fromStartTime != null && toStartTime != null) {
				Date fromSt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromStartTime);
				Date toSt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toStartTime);
				booleanBuilder.and(QGskAvailability.gskAvailability.startTime.between(fromSt, toSt));
			}
			if (fromEndTime != null && toEndTime != null) {
				Date fromEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromEndTime);
				Date toEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toEndTime);
				booleanBuilder.and(QGskAvailability.gskAvailability.endTime.between(fromEnd, toEnd));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return GskMapper.getGksAvailabilityDTOPage(
				this.service.findAllPaginated(booleanBuilder.getValue(), page, size, Order.DESC, "id"));
	}

}
//
