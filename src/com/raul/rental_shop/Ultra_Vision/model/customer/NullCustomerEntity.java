package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.time.LocalDate;

public class NullCustomerEntity extends CustomerEntity {

	private static final long serialVersionUID = 7366319144000799817L;

	public NullCustomerEntity() {
		this.setFirstname("icognito");
		this.setLastname("icognito");
		this.setPhonenumber("000 000 0000");
		this.setBirthday(LocalDate.now().toString());
		this.setStreet("Somewhere");
		this.setCity("Somewhere");
		this.setCountry("Somewhere");
		this.setMembershipPlan("Music Lover");
		this.setMembershipCardNumber(0);
		this.setBankCard("0000-0000-0000-0000");
		this.setPassword("123");
		this.setPrivilege("CUSTOMER");
	}
}
