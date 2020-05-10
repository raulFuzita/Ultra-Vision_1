package com.raul.rental_shop.Ultra_Vision.model;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;

public enum Session {

	INSTANCE;
	
	private CustomerEntity customer;
	
	public CustomerEntity get() {
		return customer;
	}
	
	public void set(final CustomerEntity customer) {
		this.customer = customer;
	}

}
