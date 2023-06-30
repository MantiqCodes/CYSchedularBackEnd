package io.giskard.scheduler.jpa.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

import io.giskard.scheduler.jpa.model.GskReservation;

public interface GskReservationService
{

	public GskReservation save(GskReservation gskReservation);

	public Page<GskReservation> findAllPaginated(Predicate predicate, Integer page, Integer size, Order sortOrder,
			String sortColumn);

	public Optional<GskReservation> findById(long id);

	public List<GskReservation> findAll();

	public void delete(long id);

	public List<GskReservation> findByStartTimeBetween(Date fromSTime, Date toSTime, String email);

}
