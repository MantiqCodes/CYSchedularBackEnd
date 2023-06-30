package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import io.giskard.scheduler.mongo.model.Reservation;

public interface MongoReservationService
{

	public Reservation save(Reservation reservation);

//	public Page<Reservation> findAllPaginated(Predicate predicate, Integer page, Integer size, Order sortOrder,
//			String sortColumn);

	public Optional<Reservation> findById(long id);

	public List<Reservation> findAll();

	public void delete(long id);

//	public List<Reservation> findByStartTimeBetween(Date fromSTime, Date toSTime, String email);
	public List<Reservation> findByActiveUserId(
//			int isActive, 
			long userId);

}
