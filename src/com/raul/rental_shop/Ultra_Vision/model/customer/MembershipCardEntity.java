package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.io.Serializable;

public class MembershipCardEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int membershipCardNumber;
	private int points;

	public int getMembershipCardNumber() {
		return this.membershipCardNumber;
	}

	public void setMembershipCardNumber(int membershipCardNumber) {
		this.membershipCardNumber = membershipCardNumber;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "MembershipCardEntity [membershipCardNumber=" + membershipCardNumber + ", points=" + points + "]";
	}
	
	
}
