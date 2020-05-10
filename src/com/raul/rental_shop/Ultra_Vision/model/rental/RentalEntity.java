package com.raul.rental_shop.Ultra_Vision.model.rental;

import java.io.Serializable;
import java.sql.Timestamp;

public class RentalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customerMembershipNumber;
	private int titleCode;
	private Timestamp rentAt;
	private Timestamp returnAt;
	private boolean isReturned;
	
	private String fullname;
	
	private String titleName;
	
	private String mediaFormat;

	public int getCustomerMembershipNumber() {
		return this.customerMembershipNumber;
	}

	public void setCustomerMembershipNumber(int customerMembershipNumber) {
		this.customerMembershipNumber = customerMembershipNumber;
	}

	public int getTitleCode() {
		return this.titleCode;
	}

	public void setTitleCode(int titleCode) {
		this.titleCode = titleCode;
	}

	public Timestamp getRentAt() {
		return this.rentAt;
	}

	public void setRentAt(Timestamp rentAt) {
		this.rentAt = rentAt;
	}

	public Timestamp getReturnAt() {
		return this.returnAt;
	}

	public void setReturnAt(Timestamp returnAt) {
		this.returnAt = returnAt;
	}

	public boolean isReturned() {
		return this.isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getMediaFormat() {
		return mediaFormat;
	}

	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	@Override
	public String toString() {
		return "RentalEntity [customerMembershipNumber=" + customerMembershipNumber + ", titleCode=" + titleCode
				+ ", rentAt=" + rentAt + ", returnAt=" + returnAt + ", isReturned=" + isReturned + ", fullname="
				+ fullname + ", titleName=" + titleName + ", mediaFormat=" + mediaFormat + "]";
	}

}
