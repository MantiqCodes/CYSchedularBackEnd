package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.giskard.scheduler.jpa.model.GskAvailability;
import io.giskard.scheduler.jpa.repository.GskAvailabilityRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GskAvailabilityIntegrationTest {

	
	@Autowired
	GskAvailabilityRepository repository;
	
	@Test
	public void whenFindByFromStatDate_toStartDateThenAvaibalilityReturend()
	{

//		Date from=null;
//		Date to=null;
//		try {
//			from=new SimpleDateFormat("yyy-MM-dd").parse("2016-01-01");
//			
//			to = new SimpleDateFormat("yyy-MM-dd").parse("2019-12-31");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<GskAvailability> resultList=repository.findAllByStartTimeBetween(from, to);
//		System.out.println("======================================================="+resultList.size());
	}
}
