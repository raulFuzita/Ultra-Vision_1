package com.raul.rental_shop.Ultra_Vision.model.title;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>TVEntity is a class to carry a tv data. This class extends<br>
 * TitleEntity class.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class TVEntity extends TitleEntity {

	/* Stores a title code. This attributes will be treated as 
	 * primary key in the class TVEntity. Keep that in mind.*/
	private int code;
	private String characterSeries; // Stores a tv character series
	
	/**
	 * This method returns the tv code.
	 * 
	 * @return a code which is a type of int.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * This method set a code value to the TVEntity
	 * attributes.
	 * 
	 * @param code is a type of an int.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * This method returns the tv characterSeries.
	 * 
	 * @return a characterSeries which is a type of String.
	 */
	public String getCharacterSeries() {
		return this.characterSeries;
	}

	/**
	 * This method set a character series value to the TVEntity
	 * attributes.
	 * 
	 * @param characterSeries is a type of a String.
	 */
	public void setCharacterSeries(String characterSeries) {
		this.characterSeries = characterSeries;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "TVEntity [code=" + code + ", characterSeries=" + characterSeries + "]";
	}

}
