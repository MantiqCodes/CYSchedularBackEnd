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
import io.giskard.scheduler.mongo.model.Availability;
import io.giskard.scheduler.mongo.model.Users;
import io.giskard.scheduler.mongo.service.MongoAvailabilityService;
import io.giskard.scheduler.mongo.service.MongoUsersService;
import io.giskard.scheduler.mongo.utils.MongoMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class MongoAvailabilityController {

	@Autowired
	MongoAvailabilityService service;
	@Autowired
	MongoUsersService usersService;

	Logger logger = LoggerFactory.getLogger(MongoAvailabilityController.class);

	@GetMapping(value = "mongoAvail/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable Long id,
			UriComponentsBuilder uriComponentsBuilder) {

		ResponseEntity<AvailabilityDTO> responseEnity = new ResponseEntity<AvailabilityDTO>(new AvailabilityDTO(),
				HttpStatus.BAD_REQUEST);
		Optional<Availability> av = this.service.findById(id);
		if (av.isPresent() && av.get().getId() != -1) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("mongoAvail/").buildAndExpand(av.get().getId()).toUri());
			responseEnity = new ResponseEntity<AvailabilityDTO>(MongoMapper.getAvailabilityDTO(av.get()), httpHeaders,
					HttpStatus.OK);
		}
		return responseEnity;

	}

	@GetMapping(value = "mongoAvail/")
	public ResponseEntity<List<AvailabilityDTO>> findAllAvailability(UriComponentsBuilder uriComponentsBuilder) {

		List<Availability> availList = new ArrayList<>();
		ResponseEntity<List<AvailabilityDTO>> responseEntity = new ResponseEntity<List<AvailabilityDTO>>(
				MongoMapper.getAvailabilityDTOList(availList), HttpStatus.NO_CONTENT);
		availList = this.service.findAll();

		if (availList != null && availList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("mongoAvail/").build().toUri());
			responseEntity = new ResponseEntity<List<AvailabilityDTO>>(MongoMapper.getAvailabilityDTOList(availList),
					httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

	@PostMapping(value = "mongoAvail/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO avlDTO,
			UriComponentsBuilder uriComponentsBuilder)

	{
		if (avlDTO != null) {
			
			Optional<Users> u =usersService.findById(avlDTO.getUsersDTO().getId());
			Availability av = MongoMapper.getAvailability(avlDTO);
			if(u.isPresent())
				av.setUsers(u.get());
			av = this.service.saveAvailability(av);
			if (this.service.findById(av.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("mongoAvail/").buildAndExpand(av.getId()).toUri());
				return new ResponseEntity<AvailabilityDTO>(MongoMapper.getAvailabilityDTO(av), httpHeaders,
						HttpStatus.OK);
			}
		}
		return null;

	}

	@DeleteMapping("mongoAvail/{id}")
	public void deleteAvailability(@PathVariable long id)

	{

		Optional<Availability> availability = this.service.findById(id);
		if (availability.isPresent() && availability.get().getId() > -1) {
			this.service.delete(availability.get().getId());

		}
	}

	@PutMapping(value = "mongoAvail/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody AvailabilityDTO avlDTO

	) {

		if (avlDTO != null && avlDTO.getId() > -1) {
			Availability avl = MongoMapper.getAvailability(avlDTO);

			Optional<Availability> avlNew = this.service.findById(avl.getId());
			if (avlNew.isPresent()) {
				Date stDate = avl.getStartTime();
				Date endDate = avl.getEndTime();
				if (endDate.compareTo(stDate) > 0) {
					Availability updatedAvl = this.service.saveAvailability(avl);
					return new ResponseEntity<Object>(MongoMapper.getAvailabilityDTO(updatedAvl), HttpStatus.OK);
				}

				return ResponseEntity.badRequest().body("End time must be latter than start time");
			}
		}

		return new ResponseEntity<Object>(avlDTO, HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = "mongoAvl/")
	public ResponseEntity<List<AvailabilityDTO>> findAvlByActiveUser(UriComponentsBuilder uriComponentsBuilder,
			@RequestParam(name = "isActive") Integer isActive, @RequestParam(name = "userId") Long userId) {

		List<Availability> availList = new ArrayList<>();
		ResponseEntity<List<AvailabilityDTO>> responseEntity = new ResponseEntity<List<AvailabilityDTO>>(
				MongoMapper.getAvailabilityDTOList(availList), HttpStatus.NO_CONTENT);
		if (isActive != null && userId != null)
			availList = this.service.findByActiveUserId(isActive, userId);

		if (availList != null && availList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(
					uriComponentsBuilder.path("mongoAvl/?isActive=" + isActive + "&userId=" + userId).build().toUri());
			responseEntity = new ResponseEntity<List<AvailabilityDTO>>(MongoMapper.getAvailabilityDTOList(availList),
					httpHeaders, HttpStatus.OK);
		}
		return responseEntity;
	}

}
