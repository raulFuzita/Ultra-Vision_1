package com.raul.rental_shop.Ultra_Vision.model.rental;

import java.sql.Timestamp;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>RentalEntity is a class to carry a rental data.<br>
 * Keep in mid that some methods return and have a Timestamp value.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class RentalEntity {

	/* Stores an id that comes from a database primary key signed to auto-increment.
	 * This attribute shouldn't be shown in your table. It'll allow you to delete
	 * a single record without deleting all records signed to a customer
	 * membership card.*/
	private int id; 
	// Stores a customer membership card number.
	private int customerMembershipNumber;
	private int titleCode; // Stores a title code.
	private Timestamp rentAt; // Stores the date and time a title was rented.
	// Stores the date and time a title should return.
	private Timestamp returnAt;
	/* This attribute is not actually used, but the system could be adapted 
	 * to track some sort of history records instead delete them.
	 * I decided to keep it if I decide to change something in my system.
	 * It'd be less painful.*/
	private boolean isReturned;
	
	private String fullname; // Stores a customer name.
	private String typePlan; // Stores a customer membership type plan.
	private String titleName; // Stores a title name.
	private String mediaFormat; // Stores a title media format.
	
	/**
	 * This method returns the id of rental record.
	 * 
	 * @return a id which is a type of int.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method set an id value to the RentalEntity
	 * attributes.
	 * 
	 * @param id is a type of an int.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns the rental customer membership number.
	 * 
	 * @return a customerMembershipNumber which is a type of int.
	 */
	public int getCustomerMembershipNumber() {
		return this.customerMembershipNumber;
	}

	/**
	 * This method set a customer membership number value to the RentalEntity
	 * attributes.
	 * 
	 * @param customerMembershipNumber is a type of an int.
	 */
	public void setCustomerMembershipNumber(int customerMembershipNumber) {
		this.customerMembershipNumber = customerMembershipNumber;
	}

	/**
	 * This method returns the title code.
	 * 
	 * @return a titleCode which is a type of int.
	 */
	public int getTitleCode() {
		return this.titleCode;
	}

	/**
	 * This method set a title code value to the RentalEntity
	 * attributes.
	 * 
	 * @param titleCode is a type of an int.
	 */
	public void setTitleCode(int titleCode) {
		this.titleCode = titleCode;
	}

	/**
	 * This method returns the rental date.
	 * 
	 * @return a rentAt which is a type of Timestamp.
	 */
	public Timestamp getRentAt() {
		return this.rentAt;
	}

	/**
	 * This method set a rent date value to the RentalEntity
	 * attributes.
	 * 
	 * @param rentAt is a type of a Timestamp.
	 */
	public void setRentAt(Timestamp rentAt) {
		this.rentAt = rentAt;
	}

	/**
	 * This method returns a Timestamp date for returned rental date.
	 * 
	 * @return a returnAt which is a type of Timestamp.
	 */
	public Timestamp getReturnAt() {
		return this.returnAt;
	}

	/**
	 * This method set a Timestamp date value to the RentalEntity
	 * attributes.
	 * 
	 * @param returnAt is a type of an Timestamp.
	 */
	public void setReturnAt(Timestamp returnAt) {
		this.returnAt = returnAt;
	}

	/**
	 * This method returns true or false to tell if a title was returned
	 * 
	 * @return a boolean, true or false.
	 */
	public boolean isReturned() {
		return this.isReturned;
	}

	/**
	 * This method set a boolean value to the class RentalEntity.
	 * 
	 * @param isReturned is a type of an boolean.
	 */
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	/**
	 * This method returns the customer full name.
	 * 
	 * @return a fullname which is a type of String.
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * This method set a full name value to the RentalEntity
	 * attributes.
	 * 
	 * @param fullname is a type of a String.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * This method returns the membership type plan.
	 * 
	 * @return a typePlan which is a type of String.
	 */
	public String getTypePlan() {
		return typePlan;
	}

	/**
	 * This method set a membership type plan value to the RentalEntity
	 * attributes.
	 * 
	 * @param typePlan is a type of an String.
	 */
	public void setTypePlan(String typePlan) {
		this.typePlan = typePlan;
	}

	/**
	 * This method returns the title name.
	 * 
	 * @return a titleName which is a type of String.
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * This method set a title name value to the RentalEntity
	 * attributes.
	 * 
	 * @param titleName is a type of an String.
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * This method returns the title media format.
	 * 
	 * @return a mediaFormat which is a type of String.
	 */
	public String getMediaFormat() {
		return mediaFormat;
	}

	/**
	 * This method set a title media format value to the RentalEntity
	 * attributes.
	 * 
	 * @param mediaFormat is a type of an String.
	 */
	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "RentalEntity [customerMembershipNumber=" + customerMembershipNumber 
				+ ", titleCode=" + titleCode + ", rentAt=" + rentAt 
				+ ", returnAt=" + returnAt + ", isReturned=" + isReturned 
				+ ", fullname=" + fullname + ", titleName=" + titleName 
				+ ", mediaFormat=" + mediaFormat + "]";
	}

}
