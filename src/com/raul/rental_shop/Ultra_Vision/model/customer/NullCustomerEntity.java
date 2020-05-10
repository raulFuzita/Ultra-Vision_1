package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.time.LocalDate;

public class NullCustomerEntity extends CustomerEntity {

	private static final long serialVersionUID = 7366319144000799817L;

	public NullCustomerEntity() {
		this.setFirstname("");
		this.setLastname("");
		this.setPhonenumber("");
		this.setBirthday(LocalDate.now().toString());
		this.setStreet("");
		this.setCity("");
		this.setCountry("");
		this.setMembershipPlan("Music Lover");
		this.setMembershipCardNumber(0);
		this.setBankCard("");
		this.setPassword("");
		this.setPrivilege("CUSTOMER");
	}
}
