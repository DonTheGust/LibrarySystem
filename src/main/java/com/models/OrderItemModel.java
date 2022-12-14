package com.models;

public class OrderItemModel {
	private int bookId;
	private int quantity;
	private BookModel book;

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;	
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BookModel getBookInfo() {
		return book;
	}
	public void setBookInfo(BookModel bookInfo) {
		this.book = bookInfo;
	}
}
