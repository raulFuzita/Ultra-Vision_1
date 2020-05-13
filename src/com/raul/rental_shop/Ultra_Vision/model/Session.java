package com.raul.rental_shop.Ultra_Vision.model;

import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.checkout.CheckoutEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>Session is an enum class singleton. It is based on singleton pattern<br>
 * for Java 9 according to the book Effective Java, Third Edition by<br>
 * Joshua Bloch. This technique avoid private final static since the<br>
 * instance is a enum value.</p>
 * 
 * @role This class works as session like for web development. It is meant to store<br>
 * informations about the user logged and its rental list of titles.
 * 
 * <p>All attributes in this class are private.<p>
 */
public enum Session {

	INSTANCE;
	
	// Stores a reference of an object of CustomerEntity
	private CustomerEntity customer; 
	// Stores a list of titles.
	private List<CheckoutEntity> titles = new ArrayList<>();
	
	/**
	 * This method will return the customer logged in in the system.
	 * 
	 * @return a customer which is type of CustomerEntity.
	 */
	public CustomerEntity get() {
		return customer;
	}
	
	/**
	 * This method will set a customer instance to the Session class.
	 * 
	 * @param customer is a type of CustomerEntity.
	 * The parameter is signed to final to avoid another variable
	 * with the same name.
	 */
	public void set(final CustomerEntity customer) {
		this.customer = customer;
	}

	/**
	 * This method will return a List signed to CheckoutEntity
	 * that keeps all Titles of the customer basket.
	 * 
	 * @return a List signed to CheckoutEntity.
	 */
	public List<CheckoutEntity> getTitles() {
		return titles;
	}

	/**
	 * This method will set a List of CheckoutEntity to the class attribute.
	 * 
	 * @param titles is a type of List signed to CheckoutEntity.
	 */
	public void setTitles(List<CheckoutEntity> titles) {
		this.titles = titles;
	}
}
