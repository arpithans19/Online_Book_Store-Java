package com.cart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_item")

public class Cart {
	
	@Transient
	public static final String SEQUENCE_NAME="cart_sequence";
	
	@Id
	private int cartId;

	private int userId;
	private int count;


	private double cartTotal;

	
	
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
}
