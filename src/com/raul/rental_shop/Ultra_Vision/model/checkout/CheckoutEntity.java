package com.raul.rental_shop.Ultra_Vision.model.checkout;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>CheckoutEntity is a class to carry part of the customer and title data.<br>
 * The information stored in this class is used to display a checkout screen<br>
 * and be able to send relevant date to the table Rental.</p>
 * 
 * <p>This class implements Comparable and overrides the method compareTo<br>
 * to be able to order the internal data. Its implementation considers<br>
 * the cost. Further details of the implementation is available at the<br>
 * method.</p>
 *
 * <p>All attributes in this class are private.<p>
 */
public class CheckoutEntity implements Comparable<CheckoutEntity> {

	private int code; // Stores a title code.
	private String name; // Stores a title name.
	private String format; // Stores a title media format.
	private int membership; // Stores the customer's membership numbers
	private String fullname; // Stores customer's full name.
	private double cost; // Stores a title cost.
	
	/**
	 * An empty Constructor. No parameters required.
	 */
	public CheckoutEntity() {}
	/**
	 * This constructor will save you to use setter and getter methods
	 * to set the attributes values.
	 * 
	 * @param customer is a type of a class CustomerEntity available at 
	 * package com.raul.rental_shop.Ultra_Vision.model.customer. 
	 * The necessary informations about the customer will be extracted
	 * and will populate the CheckoutEntity attributes.
	 * 
	 * @param title is a type of a class TitleEntity available at 
	 * com.raul.rental_shop.Ultra_Vision.model.title
	 * The necessary informations about the title will be extracted
	 * and will populate the CheckoutEntity attributes.
	 */
	public CheckoutEntity(CustomerEntity customer, TitleEntity title) {
		this.code = title.getCode();
		this.name = title.getName();
		this.format = title.getMediaFormat();
		this.membership = customer.getMembershipCardNumber();
		this.fullname = customer.getFirstname() + " " + customer.getLastname();
		this.cost = title.getCost();
	}
	
	/**
	 * This method returns the title code.
	 * 
	 * @return a title code which is a type of int.
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * This method set a code value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param code is a type of an int.
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * This method returns the title name.
	 * 
	 * @return a title name which is a type of a String.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method set a name value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param name is a type of an String.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method set a format value to the CheckoutEntity
	 * attributes.
	 * 
	 * @return a title format which is a type of a String.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * This method set a format value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param format is a type of a String.
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * This method set a membership value to the CheckoutEntity
	 * attributes.
	 * 
	 * @return a customer membership which is a type of an int.
	 */
	public int getMembership() {
		return membership;
	}
	/**
	 * This method set a membership value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param membership is a type of an int.
	 */
	public void setMembership(int membership) {
		this.membership = membership;
	}
	/**
	 * This method set a full name value to the CheckoutEntity
	 * attributes.
	 * 
	 * @return a full name membership which is a type of a String.
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * This method set a full name value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param fullname is a type of a String.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * This method set a cost value to the CheckoutEntity
	 * attributes.
	 * 
	 * @return a full name cost which is a type of a double.
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * This method set a cost value to the CheckoutEntity
	 * attributes.
	 * 
	 * @param cost is a type of a double.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * This method compares two CheckoutEntity object references
	 * and returns 1 if the class which is comparing to another
	 * class has its cost value larger than the one compared.
	 * It will returns 0 if both classes has the same cost.
	 * It will returns -1 if the class which is comparing to another
	 * class has its cost value smaller than the one compared.
	 * 
	 * @param o is a type of a CheckoutEntity.
	 * 
	 * @return an int value between -1 and 1.
	 */
	@Override
	public int compareTo(CheckoutEntity o) {
		
		if (this.getCost() > o.getCost()) {
			return 1;
		} else if (this.getCost() < o.getCost()) {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "CheckoutEntity [code=" + code + ", name=" + name + ", format=" 
				+ format + ", membership=" + membership
				+ ", fullname=" + fullname + ", cost=" + cost + "]";
	}
}
