package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import io.giskard.scheduler.mongo.model.Users;

public interface MongoUsersService {

	public Optional<Users> findById(long id);

	public Optional<Users> findUsersByEmail(String email);

	public List<Users> findAllUsers();

	public Users save(Users user);

	public void delete(long id);

	public Users updateField(long id, String fieldName, long fieldValue);

	public Users findByEmailAndPassword(String email, String password);
}
