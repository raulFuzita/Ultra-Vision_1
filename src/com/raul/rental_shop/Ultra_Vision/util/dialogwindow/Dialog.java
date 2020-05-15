package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @role
 * <p>The interface DAO signs to a generic type. It is expected when you<br>
 * when you implement it you will override all methods. This interface
 * applies to the basic CRUD (Create, REad, Update, Delete) and includes
 * one extra method, search.</p>
 *
 * @param <T> which is a generic. It is expected that whichever implements
 * this class will override the methods and implements it.
 * This interface is based on must DAO interfaces out there.
 * 
 * @apiNote This interface might change in the future. It hasn't been decided yet if 
 * the SQLException will be required or expected to be handle internally.
 */
public interface Dialog {
	
	public String getTextArea();
	public void setTextArea(String text);
	public boolean isOption();
	public String output();
}
