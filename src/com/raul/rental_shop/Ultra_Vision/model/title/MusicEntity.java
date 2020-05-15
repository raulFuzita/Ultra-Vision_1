package com.raul.rental_shop.Ultra_Vision.model.title;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>MusicEntity is a class to carry a music data. This class extends<br>
 * TitleEntity class.</p>
 * 
 * @role This class works as an entity class which represents the columns
 * in a database entity (table).
 * 
 * <p>All attributes in this class are private.<p>
 */
public class MusicEntity extends TitleEntity {

	/* Stores a title code. This attributes will be treated as 
	 * primary key in the class MusicEntity. Keep that in mind.*/
	private int code;
	private String artist; // Stores an artist name
	private String album; // Stores an album name

	/**
	 * This method returns the music code.
	 * 
	 * @return a code which is a type of int.
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * This method set a code value to the MusicEntity
	 * attributes.
	 * 
	 * @param code is a type of an int.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * This method returns the music artist.
	 * 
	 * @return a artist which is a type of String.
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * This method set a artist value to the MusicEntity
	 * attributes.
	 * 
	 * @param artist is a type of a String.
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * This method returns the music album.
	 * 
	 * @return a album which is a type of String.
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * This method set a album value to the MusicEntity
	 * attributes.
	 * 
	 * @param album is a type of a String.
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * According to the book Effective Java is considered good practice
	 * override the method toString.
	 */
	@Override
	public String toString() {
		return "MusicEntity [code=" + code + ", artist=" + artist 
				+ ", album=" + album + "]";
	}
	
}
