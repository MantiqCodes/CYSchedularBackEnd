package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import io.giskard.scheduler.mongo.model.Availability;

public interface MongoAvailabilityService {

	public Availability saveAvailability(Availability availability);

//	public Page<Availability> findAllPaginated(Predicate value, Integer page, Integer size, Order asc,
//			String string);

	public Optional<Availability> findById(long id);

	public List<Availability> findAll();

//	public	List<Availability> findByStartTimeBetween(Date fromSTime, Date toSTime, String email);
	public List<Availability> findByActiveUserId(int isActive, long userId);

	public void delete(long id);

}
