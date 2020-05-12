package com.raul.rental_shop.Ultra_Vision.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.raul.rental_shop.Ultra_Vision.model.checkout.CheckoutEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;

public enum Session {

	INSTANCE;
	
	private CustomerEntity customer;
	private List<CheckoutEntity> titles = new ArrayList<>();
	
	public CustomerEntity get() {
		return customer;
	}
	
	public void set(final CustomerEntity customer) {
		this.customer = customer;
	}

	public List<CheckoutEntity> getTitles() {
		return titles;
	}

	public void setTitles(List<CheckoutEntity> titles) {
		this.titles = titles;
	}
}
