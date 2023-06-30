package io.giskard.scheduler.jpa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.GskReservation;
import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.repository.GskReservationRepository;
import io.giskard.scheduler.jpa.repository.GskUsersRepository;

@Service
@Transactional
public class GskReservationServiceImpl implements GskReservationService
{

	Logger                   logger = LoggerFactory.getLogger(GskReservationServiceImpl.class);
	@Autowired
	GskReservationRepository gskReservationRepo;

	@Autowired
	GskUsersRepository gskUsersRepo;

	@Override
	public Optional<GskReservation> findById(long id)
	{
		return this.gskReservationRepo.findById(id);

	}

	@Override
	public List<GskReservation> findAll()
	{
		return this.gskReservationRepo.findAll();

	}

	@Override
	@Transactional
	public GskReservation save(GskReservation gskReservation)
	{
		GskReservation av = new GskReservation();
		av.setId(gskReservation.getId());
		av.setEndTime(gskReservation.getEndTime());
		av.setStartTime(gskReservation.getStartTime());
		av.setIsActive(gskReservation.getIsActive());
		av.setIsComplete(gskReservation.getIsComplete());
		av.setEmail(gskReservation.getEmail());
		av.setTitle(gskReservation.getTitle());
		if (gskReservation.getGskUsers() != null && gskReservation.getGskUsers().getId() > 0)
		{
			Optional<GskUsers> u = this.gskUsersRepo.findById(gskReservation.getGskUsers().getId());
			if (u.isPresent())
			{
				av.setGskUsers(u.get());
			}
		}
		//		else throw new IllegalArgumentException();
		av = this.gskReservationRepo.save(av);

		return av;
	}

	@Override
	@Transactional
	public void delete(long id)
	{
		this.gskReservationRepo.deleteById(id);

	}

	@Override
	public List<GskReservation> findByStartTimeBetween(Date fromSTime, Date toSTime, String email)
	{
		return this.gskReservationRepo.findAllByStartTimeBetween(fromSTime, toSTime, email);

	}

	@Override
	public Page<GskReservation> findAllPaginated(Predicate predicate, Integer page, Integer size, Order sortOrder,
			String sortColumn)
	{
		if (sortOrder.equals(Order.ASC))
			return this.gskReservationRepo.findAll(predicate,
					PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortColumn))));
		return this.gskReservationRepo.findAll(predicate,
				PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortColumn))));
	}

}
