package io.giskard.scheduler.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

import io.giskard.scheduler.jpa.dto.GskAvailabilityDTO;
import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.repository.GskAvailabilityRepository;
import io.giskard.scheduler.jpa.repository.GskUsersRepository;
import io.giskard.scheduler.jpa.utils.GskMapper;

@Transactional
@Service
public class GskAvailabilityServiceImpl implements GskAvailabilityService
{

	Logger                    logger = LoggerFactory.getLogger(GskAvailabilityService.class);
	@Autowired
	GskAvailabilityRepository gskAvailabilityRepo;
	@Autowired
	GskUsersRepository        gskUsersRepo;

	@Override
	@Transactional
	public GskAvailability saveAvailability(GskAvailability gskAvailability)
	{
		GskAvailability av = new GskAvailability();
	av.setId(gskAvailability.getId());
		av.setEndTime(gskAvailability.getEndTime());
		av.setStartTime(gskAvailability.getStartTime());
		av.setIsActive(gskAvailability.getIsActive());
		if (gskAvailability.getGskUsers() != null && gskAvailability.getGskUsers().getId() > 0)
		{
			Optional<GskUsers> u = this.gskUsersRepo.findById(gskAvailability.getGskUsers().getId());
			if (u.isPresent())
			{
				av.setGskUsers(u.get());
			}
		}
		av = this.gskAvailabilityRepo.save(av);

		return av;
	}

	@Override
	public List<GskAvailability> findAll()
	{
		return this.gskAvailabilityRepo.findAll();

	}

	@Override
	public Optional<GskAvailability> findById(long id)
	{
		return this.gskAvailabilityRepo.findById(id);

	}

	@Override
	public List<GskAvailability> findByStartTimeBetween(Date fromSTime, Date toSTime, String email)
	{
		return this.gskAvailabilityRepo.findAllByStartTimeBetween(fromSTime, toSTime, email);

	}

	@Transactional
	@Override
	public void delete(long id)
	{
		this.gskAvailabilityRepo.deleteById(id);

	}
	
	
	@Override
	public Page<GskAvailability> findAllPaginated(Predicate predicate, Integer page, Integer size, Order sortOrder,
			String sortColumn)
	{
		if (sortOrder.equals(Order.ASC))
			return gskAvailabilityRepo.findAll(predicate,
					PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortColumn))));
		return gskAvailabilityRepo.findAll(predicate, PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortColumn))));
	}

}
