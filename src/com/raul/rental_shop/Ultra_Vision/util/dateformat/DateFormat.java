package com.raul.rental_shop.Ultra_Vision.util.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static String format(String date, String from, String to) {
		
		SimpleDateFormat sdfSource = new SimpleDateFormat(from);
		
		try {
			Date dateFormat = (Date) sdfSource.parse(date);
			SimpleDateFormat sdfDestination = new SimpleDateFormat(to);
			date = sdfDestination.format(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
