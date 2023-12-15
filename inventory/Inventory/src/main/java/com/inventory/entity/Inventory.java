package com.inventory.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Inventory {

	@Transient
	public static final String SEQUENCE_NAME = "inventory_sequence";
	
	@Id
	private int inventoryId;
	private Book bookId;
	private String availabality;
	private int totalAvailableBooks;
	private boolean needRestock;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Book getBookId() {
		return bookId;
	}
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	public String getAvailabality() {
		return availabality;
	}
	public void setAvailabality(String availabality) {
		this.availabality = availabality;
	}
	public int getTotalAvailableBooks() {
		return totalAvailableBooks;
	}
	public void setTotalAvailableBooks(int totalAvailableBooks) {
		this.totalAvailableBooks = totalAvailableBooks;
	}
	public boolean isNeedRestock() {
		return needRestock;
	}
	public void setNeedRestock(boolean needRestock) {
		this.needRestock = needRestock;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	
}
