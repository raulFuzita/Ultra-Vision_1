package com.raul.rental_shop.Ultra_Vision.model.title;

import java.io.Serializable;

public class MusicEntity extends TitleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int code;
	private String artist;
	private String album;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getArtist() {
		return this.artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
	@Override
	public String toString() {
		return "MusicEntity [id=" + id + ", code=" + code + ", artist=" + artist + ", album=" + album + "]";
	}
}
