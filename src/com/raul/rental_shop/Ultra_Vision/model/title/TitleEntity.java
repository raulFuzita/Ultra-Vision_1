package com.raul.rental_shop.Ultra_Vision.model.title;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>TitleEntity is a class to carry a title data. It also generate an id (code)<br>
 * for the week tables which are the subclasses of TitleEntity, known as<br>
 * child classes as well.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class TitleEntity {

	// An enum class wil create a specific valid values to set a media format.
	public enum MediaFormat {CD, DVD, BLUERAY};

	/* Stores a title code. This attributes will be treated as 
	 * primary key in the class TitleEntity. Keep that in mind.*/
	private int code;
	private String name; // Stores the title name
	private String genre; // Stores the title genre
	private double cost; // Stores the title cost
	private String mediaFormat; // Stores the title media format
	private String typeTitle; // Stores the title type
	private String year; // Stores the title year

	/**
	 * This method returns the title code.
	 * 
	 * @return a code which is a type of int.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * This method set a code value to the TitleEntity
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
	 * @return a name which is a type of String.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method set a name value to the TitleEntity
	 * attributes.
	 * 
	 * @param name is a type of a String.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the title genre.
	 * 
	 * @return a genre which is a type of String.
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * This method set a genre value to the TitleEntity
	 * attributes.
	 * 
	 * @param genre is a type of a String.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * This method returns the title cost.
	 * 
	 * @return a cost which is a type of double.
	 */
	public double getCost() {
		return this.cost;
	}

	/**
	 * This method set a cost value to the TitleEntity
	 * attributes.
	 * 
	 * @param cost is a type of a double.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * This method returns the title mediaFormat.
	 * 
	 * @return a mediaFormat which is a type of String.
	 */
	public String getMediaFormat() {
		return this.mediaFormat;
	}

	/**
	 * This method set a mediaFormat value to the TitleEntity
	 * attributes.
	 * 
	 * @param mediaFormat is a type of a double.
	 */
	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	/**
	 * This method returns the title typeTitle.
	 * 
	 * @return a typeTitle which is a type of String.
	 */
	public String getTypeTitle() {
		return this.typeTitle;
	}

	/**
	 * This method set a typeTitle value to the TitleEntity
	 * attributes.
	 * 
	 * @param typeTitle is a type of a String.
	 */
	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	/**
	 * This method returns the title year.
	 * 
	 * @return a year which is a type of String.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * This method set a year value to the TitleEntity
	 * attributes.
	 * 
	 * @param year is a type of a String.
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "TitleEntity [code=" + code + ", name=" + name + ", genre=" + genre + ", cost=" + cost + ", mediaFormat="
				+ mediaFormat + ", typeTitle=" + typeTitle + ", year=" + year + "]";
	}
	
}
