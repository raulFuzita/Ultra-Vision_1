package com.raul.rental_shop.Ultra_Vision.model.title;

import java.io.Serializable;

public class TitleEntity implements Serializable {

	public enum MediaFormat {CD, DVD, BLUERAY};
	
	private static final long serialVersionUID = 1L;

	private int code;
	private String name;
	private String genre;
	private double cost;
	private String mediaFormat;
	private String typeTitle;
	private String year;

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getMediaFormat() {
		return this.mediaFormat;
	}

	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	public String getTypeTitle() {
		return this.typeTitle;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "TitleEntity [code=" + code + ", name=" + name + ", genre=" + genre + ", cost=" + cost + ", mediaFormat="
				+ mediaFormat + ", typeTitle=" + typeTitle + ", year=" + year + "]";
	}
	
}
