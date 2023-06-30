package io.giskard.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.service.GskAvailabilityService;
import io.giskard.scheduler.jpa.service.GskUsersService;
import io.giskard.scheduler.jpa.utils.CommonUtil;

@Component
public class GskAvailabilityLoader // implements CommandLineRunner
{
	Logger logger = LoggerFactory.getLogger(GskAvailabilityLoader.class);
	@Autowired
	GskAvailabilityService avlService;
	@Autowired
	GskUsersService usersService;

	public void createAvailabitlities() {

		GskAvailability avl = new GskAvailability();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date from = new Date();
		Date to = CommonUtil.addMinutes(from, 10);

		logger.info(from + "   -   " + to);

		GskUsers u1 = this.usersService.findById(1).get();
		GskUsers u2 = this.usersService.findById(2).get();

		for (int i = 0; i < 40; i++) {
			avl.setStartTime(from);
			avl.setEndTime(to);
			avl.setIsActive(1);
			if (i % 2 == 0)
				avl.setGskUsers(u1);
			else
				avl.setGskUsers(u2);
			this.avlService.saveAvailability(avl);
			from = to;
			to = CommonUtil.addMinutes(from, 10);
		}

	}

	public void createAvailabilitiesForAllUsers() {

		List<GskUsers> userList = this.usersService.findAllGskUsers();

		GskAvailability avl = new GskAvailability();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date from = new Date();
		Date to = CommonUtil.addMinutes(from, 10);

		logger.info(from + "   -   " + to);
for(GskUsers u:userList)
		for (int i = 0; i < 40; i++) {
			avl.setStartTime(from);
			avl.setEndTime(to);
			avl.setIsActive(1);
			avl.setGskUsers(u);
			this.avlService.saveAvailability(avl);
			from = to;
			to = CommonUtil.addMinutes(from, 10);
		}

	}
}
