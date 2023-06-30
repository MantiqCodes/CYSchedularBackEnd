package io.giskard.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.model.GskReservation;
import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.service.GskAvailabilityService;
import io.giskard.scheduler.jpa.service.GskReservationService;
import io.giskard.scheduler.jpa.service.GskUsersService;
import io.giskard.scheduler.jpa.utils.GskDataEntryHelper;

@Component
public class DataLoader implements CommandLineRunner
{

	Logger                  logger = LoggerFactory.getLogger(DataLoader.class);
	@Autowired
	private GskUsersService userService;
	@Autowired
	GskAvailabilityService  avService;
	@Autowired
	GskReservationService   rsvService;
	@Autowired
	GskAvailabilityLoader   avLoader;
	@Autowired
	GskReservationLoader    rsvLoader;

	@Override
	public void run(String... args) throws Exception
	{

		List<GskUsers> uList = this.userService.findAllGskUsers();
		if (uList == null || uList.size() == 0)
		{
			logger.info("Loading Users....");

			for (int i = 0; i < 200; i++)
				this.loadUserData();
		}
		logger.info("loading availability...");
		List<GskAvailability> avList = this.avService.findAll();
		if (avList != null && avList.size() == 0)
		{
//			this.avLoader.createAvailabitlities();
		this.avLoader.createAvailabilitiesForAllUsers();
		}
		logger.info("loading Reservation...");
		List<GskReservation> rsvList = this.rsvService.findAll();
		if (rsvList != null && rsvList.size() == 0)
			this.rsvLoader.createReservations();
	}

	public void loadUserData()
	{

		GskDataEntryHelper dataEntryHelper = new GskDataEntryHelper();
		GskUsers            u               = new GskUsers();
		String             firstName       = dataEntryHelper.getNextFirstName();
		u.setFirstName(firstName);
		u.setLastName(dataEntryHelper.getNextLastName());
		u.setEmail(dataEntryHelper.getNextEmail(firstName));
		u.setIsActive(1);
		u.setDateEntered(java.sql.Date.valueOf(LocalDate.now()));
		this.userService.save(u);

	}

}
