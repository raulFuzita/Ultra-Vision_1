package com.raul.rental_shop.Ultra_Vision.model.customer;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>CustomerEntity is a class to carry a customer data.<br>
 * This class might change in the future. It has be decided yet rather<br>
 * it'll be used a String to refers to a customer type membership plan or<br>
 * an int that represents a foreign key of another table.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class CustomerEntity {

	/* Stores a customer membership number. This attributes will be treated as 
	 * primary key in the class CustomerDAO. Keep that in mind.*/
	private int membershipCardNumber;
	/* It's a String to represent a customer type membership plan.*/
	private String membershipPlan; 
	private String password; // Stores a customer password
	private String privilege; // Stores a customer privilege
	private String firstname; // Stores a customer first name
	private String lastname; // Stores a customer last name
	/* Stores a customer phone number. This attributes is not treated as 
	 * not null value.*/
	private String phonenumber; 
	private String street; // Stores a customer street
	private String city; // Stores a customer city
	private String country; // Stores a customer country
	/* Stores a customer bank card number. This attributes is not treated as
	 * not null value.*/
	private String bankCard;
	/* Stores a customer birthday. It is expected that you will use a type date
	 * in your database.*/
	private String birthday;
	
	/**
	 * This method returns the customer membership card number.
	 * 
	 * @return a membershipCardNumber which is a type of int.
	 */
	public int getMembershipCardNumber() {
		return this.membershipCardNumber;
	}
	
	/**
	 * This method set a membership card number value to the CustomerEntity
	 * attributes.
	 * 
	 * @param membershipCardNumber is a type of an int.
	 */
	public void setMembershipCardNumber(int membershipCardNumber) {
		this.membershipCardNumber = membershipCardNumber;
	}
	
	/**
	 * This method returns the customer membershipPlan.
	 * 
	 * @return a membershipPlan which is a type of String.
	 */
	public String getMembershipPlan() {
		return this.membershipPlan;
	}

	/**
	 * This method set a membershipCardNumber value to the CustomerEntity
	 * attributes.
	 * 
	 * @param membershipPlan is a type of an String.
	 */
	public void setMembershipPlan(String membershipPlan) {
		this.membershipPlan = membershipPlan;
	}

	/**
	 * This method returns the customer first name.
	 * 
	 * @return a first name which is a type of String.
	 */
	public String getFirstname() {
		return this.firstname;
	}
	
	/**
	 * This method set a first name value to the CustomerEntity
	 * attributes.
	 * 
	 * @param first name is a type of an String.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * This method returns the customer last name.
	 * 
	 * @return a last name which is a type of String.
	 */
	public String getLastname() {
		return this.lastname;
	}
	
	/**
	 * This method set a last name value to the CustomerEntity
	 * attributes.
	 * 
	 * @param last name is a type of an String.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * This method returns the customer phone number.
	 * 
	 * @return a phone number which is a type of String.
	 */
	public String getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 * This method set a phone number value to the CustomerEntity
	 * attributes.
	 * 
	 * @param phone number is a type of an String.
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * This method returns the customer street.
	 * 
	 * @return a street which is a type of String.
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * This method set a street value to the CustomerEntity
	 * attributes.
	 * 
	 * @param street is a type of an String.
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * This method returns the customer city.
	 * 
	 * @return a city which is a type of String.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * This method set a city value to the CustomerEntity
	 * attributes.
	 * 
	 * @param city is a type of an String.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * This method returns the customer country.
	 * 
	 * @return a country which is a type of String.
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * This method set a country value to the CustomerEntity
	 * attributes.
	 * 
	 * @param country is a type of an String.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * This method returns the customer password.
	 * 
	 * @return a password which is a type of String.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method set a password value to the CustomerEntity
	 * attributes.
	 * 
	 * @param password is a type of an String.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method returns the customer privilege.
	 * 
	 * @return a privilege which is a type of String.
	 */
	public String getPrivilege() {
		return privilege;
	}

	/**
	 * This method set a privilege value to the CustomerEntity
	 * attributes.
	 * 
	 * @param privilege is a type of an String.
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/**
	 * This method returns the customer bank card.
	 * 
	 * @return a bank card which is a type of String.
	 */
	public String getBankCard() {
		return bankCard;
	}
	
	/**
	 * This method set a bank card value to the CustomerEntity
	 * attributes.
	 * 
	 * @param bank card is a type of an String.
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	/**
	 * This method returns the customer birthday.
	 * 
	 * @return a birthday which is a type of String.
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * This method set a birthday value to the CustomerEntity
	 * attributes.
	 * 
	 * @param birthday is a type of an String.
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "CustomerEntity [membershipCardNumber=" + membershipCardNumber 
				+ ", membershipPlan=" + membershipPlan
				+ ", password=" + password + ", privilege=" + privilege 
				+ ", firstname=" + firstname + ", lastname="
				+ lastname + ", phonenumber=" + phonenumber + ", street=" 
				+ street + ", city=" + city + ", country="
				+ country + ", bankCard=" + bankCard + ", birthday=" 
				+ birthday + "]";
	}
}
