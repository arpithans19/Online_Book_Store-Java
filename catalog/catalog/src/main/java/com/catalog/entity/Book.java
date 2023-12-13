package com.catalog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
	
	@Transient
	public static final String SEQUENCE_NAME = "books_sequence";
	
	@Id
	private int bookId;
	private String title;
	private String author;
	private Long ISBN;
	private double price;
	private String availabality;
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public Long getISBN() {
		return ISBN;
	}
	public void setISBN(Long iSBN) {
		ISBN = iSBN;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAvailabality() {
		return availabality;
	}
	public void setAvailabality(String availabality) {
		this.availabality = availabality;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	
	
	
	

}
