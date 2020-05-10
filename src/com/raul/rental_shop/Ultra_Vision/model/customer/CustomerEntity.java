package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.io.Serializable;

public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int membershipCardNumber;
	private String membershipPlan;
	private String password;
	private String privilege;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String street;
	private String city;
	private String country;
	private String bankCard;
	private String birthday;
	

	public int getMembershipCardNumber() {
		return this.membershipCardNumber;
	}

	public void setMembershipCardNumber(int membershipCardNumber) {
		this.membershipCardNumber = membershipCardNumber;
	}

	public String getMembershipPlan() {
		return this.membershipPlan;
	}

	public void setMembershipPlan(String membershipPlan) {
		this.membershipPlan = membershipPlan;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "CustomerEntity [membershipCardNumber=" + membershipCardNumber + ", membershipPlan=" + membershipPlan
				+ ", password=" + password + ", privilege=" + privilege + ", firstname=" + firstname + ", lastname="
				+ lastname + ", phonenumber=" + phonenumber + ", street=" + street + ", city=" + city + ", country="
				+ country + ", bankCard=" + bankCard + ", birthday=" + birthday + "]";
	}
}
