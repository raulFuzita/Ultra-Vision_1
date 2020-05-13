package com.raul.rental_shop.Ultra_Vision.model.customer;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>MembershipCardEntity is a class that will track a customer points of
 * loyalty card.</p>
 *
 * <p>All attributes in this class are private.<p>
 */
public class MembershipCardEntity {

	private int membershipCardNumber; // Stores a customer membership number
	private int points; // Stores a customer points

	/**
	 * This method returns the customer membership number.
	 * 
	 * @return membershipCardNumber which is a type of int.
	 */
	public int getMembershipCardNumber() {
		return this.membershipCardNumber;
	}

	/**
	 * This method set a code value to the MembershipCardEntity
	 * attributes.
	 * 
	 * @param membershipCardNumber is a type of an int.
	 */
	public void setMembershipCardNumber(int membershipCardNumber) {
		this.membershipCardNumber = membershipCardNumber;
	}

	/**
	 * This method returns the customer points.
	 * 
	 * @return points which is a type of int.
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * This method set a code value to the MembershipCardEntity
	 * attributes.
	 * 
	 * @param points is a type of an int.
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "MembershipCardEntity [membershipCardNumber=" + membershipCardNumber 
				+ ", points=" + points + "]";
	}
	
	
}
