package io.giskard.scheduler.controller.mongo;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.giskard.scheduler.mongo.model.Users;
import io.giskard.scheduler.mongo.service.MongoUsersService;
import io.giskard.scheduler.mongo.utils.MongoMapper;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })

@RestController
public class MongoUsersController {

	@Autowired
	private MongoUsersService service;

	@GetMapping(value = "mongoUsers/{id}")
	public ResponseEntity<Users> getGskUserById(@PathVariable long id, UriComponentsBuilder uriComponentsBuilder) {
		Optional<Users> UsersOpt = service.findById(id);

		ResponseEntity<Users> responseEntity = new ResponseEntity<>(new Users(), HttpStatus.BAD_REQUEST);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (UsersOpt.isPresent()) {
			httpHeaders.setLocation(
					uriComponentsBuilder.path("/Users/").buildAndExpand(UsersOpt.get().getId()).toUri());
			responseEntity = new ResponseEntity<Users>(UsersOpt.get(), httpHeaders, HttpStatus.OK);

		}
		return responseEntity;
	}	
	@GetMapping(value = "mongoLogin/")
	public ResponseEntity<Users> getUserByEmailAndPassword(@RequestParam(name="email" ) String email,@RequestParam(name="password" ) String password, UriComponentsBuilder uriComponentsBuilder) {
		Users UsersOpt = service.findByEmailAndPassword(email, password);

		ResponseEntity<Users> responseEntity = new ResponseEntity<>(new Users(), HttpStatus.BAD_REQUEST);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (UsersOpt!=null) {
			httpHeaders.setLocation(
					uriComponentsBuilder.path("/Users/").buildAndExpand(UsersOpt.getId()).toUri());
			responseEntity = new ResponseEntity<Users>(UsersOpt, httpHeaders, HttpStatus.OK);
return responseEntity;
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "mongoUsers/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Users>> getAllUsers(UriComponentsBuilder uriComponentsBuilder) {
		ResponseEntity<List<Users>> responseEntity = new ResponseEntity<>(new ArrayList<Users>(),
				HttpStatus.NO_CONTENT);

		List<Users> UsersList = this.service.findAllUsers();

		if (UsersList != null && UsersList.size() > 0) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("mongoUsers/").build().toUri());
			responseEntity = new ResponseEntity<List<Users>>(UsersList, httpHeaders, HttpStatus.OK);
		}
		return responseEntity;

	}

	@PostMapping(value = "mongoUsers/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Users> create(@RequestBody Users users, UriComponentsBuilder uriComponentsBuilder)

	{
		if (users != null) {
			Users rv = MongoMapper.getUsers(users);
			rv = this.service.save(rv);
			if (this.service.findById(rv.getId()).isPresent()) {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uriComponentsBuilder.path("mongoUsers/").buildAndExpand(rv.getId()).toUri());
				return new ResponseEntity<Users>(rv, httpHeaders, HttpStatus.OK);
			}
		}
		return null;

	}

	@PutMapping(value = "mongoUsers/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAvailability(@Valid @RequestBody Users avlDTO

	) {

		if (avlDTO != null && avlDTO.getId() > -1) {
			Users avl = MongoMapper.getUsers(avlDTO);
			Optional<Users> avlNew = this.service.findById(avl.getId());
			if (avlNew.isPresent() && avlNew.get().getId() > -1) {
				Users updatedAvl = this.service.save(avl);
				return new ResponseEntity<Object>(updatedAvl, HttpStatus.OK);
			}
		}
		return  ResponseEntity.badRequest().body("Update failed: Id cannot be null ");

	}

	@DeleteMapping("mongoUsers/{id}")
	public void delete(@PathVariable long id)

	{

		Optional<Users> Users = this.service.findById(id);
		if (Users.isPresent() && Users.get().getId() > -1) {
			this.service.delete(Users.get().getId());

		}
	}
}
