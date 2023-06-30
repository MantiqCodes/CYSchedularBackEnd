package io.giskard.scheduler.mongo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import io.giskard.scheduler.mongo.model.Users;

@Service

public class MongoUsersServiceImpl implements MongoUsersService {
	@Autowired
	private MongoTemplate mongoTemplate;
	Logger logger = LoggerFactory.getLogger(MongoUsersService.class);

	@Override
	public Users save(Users user) {
		return this.mongoTemplate.save(user);

	}

	@Override
	public Optional<Users> findById(long id) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		List<Users> uList = this.mongoTemplate.find(query, Users.class);
		logger.debug("**MongoUsersServiceImpl->findById()********** userList size="+uList.size());
		if (uList != null && uList.size() > 0)
			return Optional.of(uList.get(0));
		return Optional.of(null);
	}

	@Override
	public Optional<Users> findUsersByEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Email").is(email));
		List<Users> uList = this.mongoTemplate.find(query, Users.class);
		if (uList != null && uList.size() > 0)

			return Optional.of(uList.get(0));

		return Optional.of(null);
	}

	@Override
	public List<Users> findAllUsers() {
		return mongoTemplate.findAll(Users.class);
	}

	@Override
	public void delete(long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Id").is(id));
		this.mongoTemplate.remove(query, Users.class);
	}

	@Override
	public Users updateField(long id, String fieldName, long fieldValue) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Id").is(id));
		Update update = new Update();
		update.set(fieldName, fieldValue);
		Users u = this.mongoTemplate.findAndModify(query, update, Users.class);
		return u;
	}

	@Override
	public Users findByEmailAndPassword(String email, String password) {

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		query.addCriteria(Criteria.where("password").is(password));
		Users u = this.mongoTemplate.findOne(query, Users.class);
		return u;
	}

}
