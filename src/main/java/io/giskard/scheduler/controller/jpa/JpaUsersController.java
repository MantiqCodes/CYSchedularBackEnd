package io.giskard.scheduler.controller.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.service.GskUsersService;
import io.giskard.scheduler.jpa.utils.GskMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })

@RestController
public class JpaUsersController {

	@Autowired
	private GskUsersService service;

	@GetMapping(value = "jpaUsers/{id}")
	public ResponseEntity<GskUsers> getGskUserById(@PathVariable long id, UriComponentsBuilder uriComponentsBuilder) {
		Optional<GskUsers> gskUsersOpt = service.findById(id);

		ResponseEntity<GskUsers> responseEntity = new ResponseEntity<>(new GskUsers(), HttpStatus.BAD_REQUEST);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (gskUsersOpt.isPresent()) {
			httpHeaders.setLocation(
					uriComponentsBuilder.path("/gskusers/").buildAndExpand(gskUsersOpt.get().getId()).toUri());
			responseEntity = new ResponseEntity<GskUsers>(gskUsersOpt.get(), httpHeaders, HttpStatus.OK);

		}
		return responseEntity;
	}

	@GetMapping(value = "jpaUsers/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<GskUsers>> getAllGskUsers(UriComponentsBuilder uriComponentsBuilder) {
		ResponseEntity<List<GskUsers>> responseEntity = new ResponseEntity<>(new ArrayList<GskUsers>(),
				HttpStatus.NO_CONTENT);

		List<GskUsers> gskUsersList = this.service.findAllGskUsers();

		if (gskUsersList != null && gskUsersList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("jpaUsers/").build().toUri());
			responseEntity = new ResponseEntity<List<GskUsers>>(gskUsersList, httpHeaders, HttpStatus.OK);
		}
		return responseEntity;

	}

	@PostMapping(value = "jpaUsers/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GskUsers> create(@RequestBody GskUsers users, UriComponentsBuilder uriComponentsBuilder)

	{
		if (users != null) {
			GskUsers rv = GskMapper.getGskUsers(users);
			rv = this.service.save(rv);
			if (this.service.findById(rv.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("jpaUsers/").buildAndExpand(rv.getId()).toUri());
				return new ResponseEntity<GskUsers>(rv, httpHeaders, HttpStatus.OK);
			}
		}
		return null;

	}

	@PutMapping(value = "jpaUsers/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody GskUsers avlDTO

	) {

		if (avlDTO != null && avlDTO.getId() > -1) {
			GskUsers avl = GskMapper.getGskUsers(avlDTO);
			Optional<GskUsers> avlNew = this.service.findById(avl.getId());
			if (avlNew.isPresent() && avlNew.get().getId() > -1) {
				GskUsers updatedAvl = this.service.save(avl);
				return new ResponseEntity<Object>(updatedAvl, HttpStatus.OK);
			}
		}
		return  ResponseEntity.badRequest().body("Update failed: Id cannot be null ");

	}

	@DeleteMapping("jpaUsers/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id)

	{

		Optional<GskUsers> gskUsers = this.service.findById(id);
		if (gskUsers.isPresent() && gskUsers.get().getId() > -1) {
			this.service.delete(gskUsers.get().getId());
			if (this.service.findById(gskUsers.get().getId()).isPresent())
				return ResponseEntity.unprocessableEntity()
						.body("Failed to delte GskUsers with id =" + gskUsers.get().getId());

		}
		return null;
	}
}
