package io.giskard.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.giskard.scheduler.jpa.model.GskReservation;
import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.service.GskReservationService;
import io.giskard.scheduler.jpa.service.GskUsersService;
import io.giskard.scheduler.jpa.utils.CommonUtil;
import io.giskard.scheduler.jpa.utils.GskDataEntryHelper;

@Component
public class GskReservationLoader // implements CommandLineRunner
{

	Logger logger = LoggerFactory.getLogger(GskAvailabilityLoader.class);

	@Autowired
	GskReservationService reservService;

	@Autowired
	GskUsersService usersService;

	public void createReservations() {
		GskReservation rsv = new GskReservation();
		GskUsers u1 = this.usersService.findById(1).get();
		GskUsers u2 = this.usersService.findById(2).get();
		Date from = new Date();
		Date to = CommonUtil.addMinutes(from, 15);
		for (int i = 0; i < 40; i++) {
			rsv.setStartTime(from);
			rsv.setEndTime(to);
			rsv.setEmail("ml.giskard@gmail.com");
			rsv.setTitle(GskDataEntryHelper.getNextTittle());
			if (i % 2 == 0)
				rsv.setGskUsers(u1);
			else
				rsv.setGskUsers(u2);
			this.reservService.save(rsv);
		}



	}
	
}
