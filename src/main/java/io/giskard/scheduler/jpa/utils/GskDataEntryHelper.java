package io.giskard.scheduler.jpa.utils;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class GskDataEntryHelper
{

	

	public String[] firstNameList =
	{
			"Tom", "Max", "Anna", "Hanna", "Mike", "Duke", "Fred", "Tim", "Paul", "Luke", "Tobias", "Timi", "Michelle",
			"Thomas", "Andrew"
	};
	public static  String [] titleList= {
			"Interview Meeting",
			"New client setup meeting",
			"ML Model Performacne followup meeting",
			"Feature Engineering meeting",
			"EDA Meeting",
			"Spark Model integration meeting"
			
			
	};
	public static String getNextTittle()
	{
		int findex = ThreadLocalRandom.current().nextInt(0, titleList.length);

		return titleList[findex];
		
		
	}
	public String[] lastNameList  =
	{
			"Smith", "Taylor", "Williams", "Hammer", "Lewis", "Jones", "Evans", "Harris", "Mayer", "Schildt"
	};

	public String[] emailDomainList =
	{
			"@gmail.com", "@yahoo.com", "@hotmail.com", "@live.come", "@retailDiscount.com"
	};

	public String getNextFirstName()
	{
		int findex = ThreadLocalRandom.current().nextInt(0, firstNameList.length);

		return firstNameList[findex];
	}

	public String getNextLastName()
	{
		int lindex = ThreadLocalRandom.current().nextInt(0, lastNameList.length);

		return lastNameList[lindex];
	}

	public String getNextEmail(String firstName)
	{
		String email = firstName + "." + getNewUuId()
				+ emailDomainList[ThreadLocalRandom.current().nextInt(0, emailDomainList.length)];
		return email;
	}

	public String getNewUuId()
	{
		double d =Math.random();
		d= d*(0.98-0.12)+0.1;
		DecimalFormat decimalFormat =new DecimalFormat("#.####");
		return decimalFormat.format(d);
	}

}
