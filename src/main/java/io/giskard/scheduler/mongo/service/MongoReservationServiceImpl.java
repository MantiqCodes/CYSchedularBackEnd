package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import io.giskard.scheduler.mongo.model.Reservation;
import io.giskard.scheduler.mongo.model.Users;

@Service
@Transactional
public class MongoReservationServiceImpl implements MongoReservationService {

	Logger logger = LoggerFactory.getLogger(MongoReservationServiceImpl.class);
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoUsersService mongoUsersService;

	@Override
	public Optional<Reservation> findById(long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Id").is(id));
		List<Reservation> rsvList = mongoTemplate.find(query, Reservation.class);
		if (rsvList != null && rsvList.size() > 0)
			return Optional.of(rsvList.get(0));
		return Optional.of(null);

	}

	@Override
	public List<Reservation> findAll() {
		return this.mongoTemplate.findAll(Reservation.class);

	}

	@Override
	public Reservation save(Reservation reservation) {
		Reservation av = new Reservation();
		av.setId(reservation.getId());
		av.setEndTime(reservation.getEndTime());
		av.setStartTime(reservation.getStartTime());
		av.setIsActive(reservation.getIsActive());
		av.setIsComplete(reservation.getIsComplete());
		av.setEmail(reservation.getEmail());
		av.setTitle(reservation.getTitle());
		if (reservation.getUsers() != null && reservation.getUsers().getId() > 0) {
			Optional<Users> u = this.mongoUsersService.findById(reservation.getUsers().getId());
			if (u.isPresent()) {
				av.setUsers(u.get());
			}
		}
		// else throw new IllegalArgumentException();
		av = this.mongoTemplate.save(av);

		return av;
	}

	@Override
	public void delete(long id) {
		this.mongoTemplate.remove(new Query().addCriteria(Criteria.where("Id").is(id)), Reservation.class);
	}

	@Override
	public List<Reservation> findByActiveUserId(
//			int isActive, 
			long userId) {

		Query query = new Query();
//		query.addCriteria(Criteria.where("isActive").is(isActive));
		query.addCriteria(Criteria.where("users.id").is(userId));
		return this.mongoTemplate.find(query, Reservation.class);

	}

}
