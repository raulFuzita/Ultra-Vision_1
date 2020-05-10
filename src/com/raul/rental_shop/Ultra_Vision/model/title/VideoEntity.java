package com.raul.rental_shop.Ultra_Vision.model.title;

import java.io.Serializable;

public class VideoEntity extends TitleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int code;
	private String director;
	private String description;

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

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "VideoEntity [id=" + id + ", code=" + code + ", director=" + director + ", description=" + description
				+ "]";
	}
	
}
