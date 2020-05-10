package com.raul.rental_shop.Ultra_Vision.model.title;

import java.io.Serializable;

public class TVEntity extends TitleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int code;
	private String characterSeries;

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

	public String getCharacterSeries() {
		return this.characterSeries;
	}

	public void setCharacterSeries(String characterSeries) {
		this.characterSeries = characterSeries;
	}

	@Override
	public String toString() {
		return "TVEntity [id=" + id + ", code=" + code + ", characterSeries=" + characterSeries + "]";
	}
	
	
}
