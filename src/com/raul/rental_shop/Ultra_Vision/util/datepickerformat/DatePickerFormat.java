package com.raul.rental_shop.Ultra_Vision.util.datepickerformat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 * @author The solution is originally from Code.Makery but it is adapted in a 
 * class with a static method by Raul Macedo Fuzita.
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>DatePickerFormat is a class that is able to change the date format of<br>
 * a JavaFx 8 DatePicker. For example, a format of yyyy/MM/dd can easily be 
 * format to dd/MM/YYYY or even works with time such as dd/MM/yyyy hh:mm:ss to<br>
 * hh:mm:ss dd/MM/yyyy.
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 */
public class DatePickerFormat {

	
	/**
	 * This method is able to change a DataPicker format. This method doesn't<br>
	 * require to instantiate the class since it is a static method.
	 * 
	 * The original code is available at: https://code.makery.ch/blog/javafx-8-date-picker/
	 * All credits go to code.makery.
	 * 
	 * @param datePicker is a type of JavaFX8 DatePiker.
	 * @param pattern is the format you aim.
	 */
	public static void format(DatePicker datePicker, String pattern) {

		datePicker.setPromptText(pattern.toLowerCase());
		
		datePicker.setConverter(new StringConverter<LocalDate>() {
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
	}
}
