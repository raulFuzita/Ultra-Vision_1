package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.time.LocalDate;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>NullCustomerEntity is a class that plays the Null Object pattern role.<br>
 * It is meant to work with a class Optional. <br>
 * Such technique is commented in the book Design Patterns com Java, Projeto<br>
 * Orientado a Objeto guia por Padrões by Eduardo Guerra.<br>
 * It is published by Casa do Código.</p>
 * 
 * @role It is a Null Object class.
 *
 * <p>All attributes in this class aren't accessible. It accesses the super<br>
 * class attributes by using setters.<p>
 */
public class NullCustomerEntity extends CustomerEntity {

	/**
	 * Simple constructor that sets default values. This class cannot be
	 * used to store information in a database since the membership number
	 * which refers to a primary key is zero.
	 * The values of the attributes are irrelevant it is only for working as 
	 * Null Object class.
	 * 
	 * If you want to know the default values you should call the toString method.
	 */
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

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "NullCustomerEntity [getMembershipCardNumber()=" + getMembershipCardNumber() 
				+ ", getMembershipPlan()="
				+ getMembershipPlan() + ", getFirstname()=" + getFirstname() 
				+ ", getLastname()=" + getLastname() + ", getPhonenumber()=" 
				+ getPhonenumber() + ", getStreet()=" + getStreet() + ", getCity()=" 
				+ getCity() + ", getCountry()=" + getCountry() + ", getPassword()=" 
				+ getPassword() + ", getPrivilege()=" + getPrivilege() + ", getBankCard()=" 
				+ getBankCard() + ", getBirthday()=" + getBirthday() + ", toString()=" 
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" 
				+ hashCode() + "]";
	}
}
