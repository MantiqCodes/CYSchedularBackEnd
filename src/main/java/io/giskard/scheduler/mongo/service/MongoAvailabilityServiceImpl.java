package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import io.giskard.scheduler.mongo.model.Availability;
import io.giskard.scheduler.mongo.model.Users;

@Service
public class MongoAvailabilityServiceImpl implements MongoAvailabilityService {

	Logger logger = LoggerFactory.getLogger(MongoAvailabilityService.class);
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoUsersService mongoUsersService;

	@Override
	public Availability saveAvailability(Availability availability) {
		Availability av = new Availability();
		av.setId(availability.getId());
		av.setEndTime(availability.getEndTime());
		av.setStartTime(availability.getStartTime());
		av.setIsActive(availability.getIsActive());
		if (availability.getUsers() != null && availability.getUsers().getId() > 0) {
			Optional<Users> u = this.mongoUsersService.findById(availability.getUsers().getId());
			if (u.isPresent()) {
				av.setUsers(u.get());
			}
		}
		av = this.mongoTemplate.save(av);

		return av;
	}

	@Override
	public List<Availability> findAll() {
		return this.mongoTemplate.findAll(Availability.class);

	}

	@Override
	public Optional<Availability> findById(long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Id").is(id));
		List<Availability> avList = mongoTemplate.find(query, Availability.class);
		if (avList != null && avList.size() > 0)
			return Optional.of(avList.get(0));

		return Optional.of(null);

	}

	@Override
	public void delete(long id) {

		this.mongoTemplate.remove(new Query().addCriteria(Criteria.where("Id").is(id)), Availability.class);

	}

	@Override
	public List<Availability> findByActiveUserId(int isActive, long userId) {

		Query query = new Query();
		query.addCriteria(Criteria.where("isActive").is(isActive));
		query.addCriteria(Criteria.where("users.id").is(userId));

		return mongoTemplate.find(query, Availability.class);
	}

}
