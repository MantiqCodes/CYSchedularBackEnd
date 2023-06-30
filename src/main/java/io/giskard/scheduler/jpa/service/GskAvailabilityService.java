package io.giskard.scheduler.jpa.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

import io.giskard.scheduler.jpa.model.GskAvailability;

public interface GskAvailabilityService
{

	public GskAvailability saveAvailability(GskAvailability gskAvailability);

	public Page<GskAvailability> findAllPaginated(Predicate value, Integer page, Integer size, Order asc,
			String string);

	public Optional<GskAvailability> findById(long id);

	public List<GskAvailability> findAll();

	public	List<GskAvailability> findByStartTimeBetween(Date fromSTime, Date toSTime, String email);

	public void delete(long id);

}
