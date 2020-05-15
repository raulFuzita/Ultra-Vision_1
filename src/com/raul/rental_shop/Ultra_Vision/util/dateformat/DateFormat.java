package com.raul.rental_shop.Ultra_Vision.util.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>DateFormat is a class to fix easily format data type when you have it<br>
 * stored in a String variable. Passing an expected data format and what you<br>
 * expect it to become it is possible to convert it to any date format.</p>
 * 
 * @role This class converts date format.
 * 
 */
public class DateFormat {

	/**
	 * This method can be called without instantiating the class since
	 * the method is static.
	 * 
	 * @param date is a type of String. It's the value to be format.
	 * @param from is a type of String. It refers to the input pattern.
	 * @param to is a type of String. It refers to the output pattern.
	 * 
	 * @return a String value formated according to the output pattern.
	 */
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
