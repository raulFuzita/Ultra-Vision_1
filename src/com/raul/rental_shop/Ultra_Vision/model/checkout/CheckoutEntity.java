package com.raul.rental_shop.Ultra_Vision.model.checkout;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;

public class CheckoutEntity implements Comparable<CheckoutEntity> {

	private int code;
	private String name;
	private String format;
	private int membership;
	private String fullname;
	private double cost;
	
	public CheckoutEntity() {}
	public CheckoutEntity(CustomerEntity customer, TitleEntity title) {
		this.code = title.getCode();
		this.name = title.getName();
		this.format = title.getMediaFormat();
		this.membership = customer.getMembershipCardNumber();
		this.fullname = customer.getFirstname() + " " + customer.getLastname();
		this.cost = title.getCost();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
		this.membership = membership;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "CheckoutEntity [code=" + code + ", name=" + name + ", format=" + format + ", membership=" + membership
				+ ", fullname=" + fullname + ", cost=" + cost + "]";
	}
	@Override
	public int compareTo(CheckoutEntity o) {
		
		if (this.getCost() > o.getCost()) {
			return 1;
		} else if (this.getCost() < o.getCost()) {
			return -1;
		}
		
		return 0;
	}
	
	
	
}
