package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.interfaces.Listable;

public class OrderModel implements Listable {
	private int id;
	private int clId;
	private ClientModel client;
	private Date date;
	private String address;
	private String payment;
	private List<OrderItemModel> items = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClId() {
		return clId;
	}
	public void setClId(int clId) {
		this.clId = clId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<OrderItemModel> getItems() {
		return items;
	}
	public void setItems(List<OrderItemModel> items) {
		this.items = items;
	}
	
	public void addBook(OrderItemModel item) {
		items.add(item);
	}
	public ClientModel getClient() {
		return client;
	}
	public void setClient(ClientModel client) {
		this.client = client;
	}
}
