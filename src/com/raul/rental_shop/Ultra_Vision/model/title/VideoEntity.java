package com.raul.rental_shop.Ultra_Vision.model.title;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>VideoEntity is a class to carry a music data. This class extends<br>
 * TitleEntity class.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class VideoEntity extends TitleEntity {

	/* Stores a title code. This attributes will be treated as 
	 * primary key in the class VideoEntity. Keep that in mind.*/
	private int code;
	private String director; // Stores the video director
	private String description; // Stores the video description

	/**
	 * This method returns the video code.
	 * 
	 * @return a code which is a type of int.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * This method set a code value to the VideoEntity
	 * attributes.
	 * 
	 * @param code is a type of an int.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * This method returns the video director.
	 * 
	 * @return a director which is a type of String.
	 */
	public String getDirector() {
		return this.director;
	}

	/**
	 * This method set a director value to the VideoEntity
	 * attributes.
	 * 
	 * @param director is a type of a String.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * This method returns the video description.
	 * 
	 * @return a description which is a type of String.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This method set a description value to the VideoEntity
	 * attributes.
	 * 
	 * @param description is a type of a String.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "VideoEntity [code=" + code + ", director=" + director + ", description=" + description + "]";
	}
	
}
