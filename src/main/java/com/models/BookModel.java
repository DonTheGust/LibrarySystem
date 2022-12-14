package com.models;

import com.interfaces.Listable;

public class BookModel implements Listable {
	private int id;
	private int puId;
	private int auId;
	private String title;
	private String author;
	private Double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuId() {
		return puId;
	}
	public void setPuId(int puId) {
		this.puId = puId;
	}
	public int getAuId() {
		return auId;
	}
	public void setAuId(int auId) {
		this.auId = auId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
