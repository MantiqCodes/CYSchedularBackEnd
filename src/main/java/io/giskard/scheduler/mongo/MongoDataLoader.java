package io.giskard.scheduler.mongo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import io.giskard.scheduler.jpa.utils.CommonUtil;
import io.giskard.scheduler.jpa.utils.GskDataEntryHelper;
import io.giskard.scheduler.mongo.model.Availability;
import io.giskard.scheduler.mongo.model.Reservation;
import io.giskard.scheduler.mongo.model.Users;
import io.giskard.scheduler.mongo.service.MongoAvailabilityService;
import io.giskard.scheduler.mongo.service.MongoReservationService;
import io.giskard.scheduler.mongo.service.MongoUsersService;
import io.giskard.scheduler.mongo.utils.ROLE;

@Component
public class MongoDataLoader implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(MongoDataLoader.class);
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoUsersService mongtoUsersService;
	@Autowired
	MongoAvailabilityService mongoAvailabilityService;
	@Autowired
	MongoReservationService mongoReservationService;
	public static long USER_ID = 1;
	public static long AVAILABILITY_ID = 1;
	public static long RESERVATION_ID = 1;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		List<Users> uList = mongoTemplate.findAll(Users.class);
		if (uList == null || uList.size() == 0) {
			logger.info("********** Createing Mongo User with ID =" + USER_ID);

			for (int i = 0; i < 40; i++) {
				Users u =createUsers();
				if(i%10==0)
					u.setRole(ROLE.ROLE_ADMIN.toString());
				else
					u.setRole(ROLE.ROLE_USER.toString());
				mongoTemplate.save(u);
				}
		}
		
		logger.info("********** Createing Mongo Availability with ID =" + AVAILABILITY_ID);

		createAvailabitlity();

		logger.info("********** Createing Mongo Reservation with ID =" + RESERVATION_ID);

		createReservations();

	}

	public Users createUsers() {
		GskDataEntryHelper dataEntryHelper = new GskDataEntryHelper();
		Users u = new Users();
		u.setId(USER_ID);
		String firstName = dataEntryHelper.getNextFirstName();
		u.setFirstName(firstName);
		u.setLastName(dataEntryHelper.getNextLastName());
		u.setEmail("u"+USER_ID+"@gmail.com");
		u.setPassword("u"+USER_ID+"$");
		u.setIsActive(1);
		u.setDateEntered(java.sql.Date.valueOf(LocalDate.now()));
		USER_ID++;
		return u;

	}

	public void createAvailabitlity() {

		List<Users> uList = this.mongtoUsersService.findAllUsers();

		for (Users u : uList) {
			Date from = new Date();
			Date to = CommonUtil.addMinutes(from, 10);

			List<Availability> avlList = mongoAvailabilityService.findByActiveUserId(1, u.getId());
			if (avlList == null || avlList.size() == 0) {
				int limit = new Random().nextInt(Math.abs(40 - (int) u.getId()) + 1) + 25;

				for (int i = 1; i <= limit; i++) {
					Availability avl = new Availability();

					avl.setId(AVAILABILITY_ID++);
					avl.setStartTime(from);
					avl.setEndTime(to);
					avl.setIsActive(1);

					avl.setUsers(u);
					this.mongoTemplate.save(avl);
					this.mongtoUsersService.updateField(u.getId(), "availCount", u.getAvailCount() + i);

					from = to;
					to = CommonUtil.addMinutes(from, 10);
				}

			}

		}

	}

	public void createReservations() {

		List<Users> uList = this.mongtoUsersService.findAllUsers();

		for (Users u : uList) {
			Date from = new Date();
			Date to = CommonUtil.addMinutes(from, 10);

			List<Reservation> rsvList = mongoReservationService.findByActiveUserId(u.getId());
			if (rsvList == null || rsvList.size() == 0) {

				int limit = new Random().nextInt(Math.abs(40 - (int) u.getId() )+ 1) + 25;

				for (int i = 1; i <= limit; i++) {
					Reservation rsv = new Reservation();

					rsv.setId(RESERVATION_ID++);
					rsv.setStartTime(from);
					rsv.setEndTime(to);
					rsv.setEmail("ml.giskard@gmail.com");
					rsv.setTitle(GskDataEntryHelper.getNextTittle());
					rsv.setUsers(u);
					this.mongoTemplate.save(rsv);
					this.mongtoUsersService.updateField(u.getId(), "reservCount", u.getReservCount() + i);

					from = to;
					to = CommonUtil.addMinutes(from, 10);
				}

				// update availList and reservList count in User 
				List<Availability> aList=this.mongoAvailabilityService.findByActiveUserId(1, u.getId());
				List<Reservation> rList=this.mongoReservationService.findByActiveUserId(u.getId());
				u.setAvailCount(aList.size());
				u.setReservCount(rList.size());
				mongoTemplate.save(u);
			}
		}
	}

}
