package io.giskard.scheduler.jpa.utils;

import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtil {

	/*
	 * 
	 * RFC 5322 email validation
	 */
	public static boolean isValidEmail(String email)
	{
		
		return Pattern.compile("\"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$\"")
		.matcher(email)
		.matches();
	}
	
	
	
	
	public static Date addMinutes(Date date,int minutes)
	{
		long dateInMs=date.getTime();
		long minutesInMs=60*1000*minutes;
		long newDateInMs=dateInMs+minutesInMs;
		
		return new  Date(newDateInMs);
		
	}
}
