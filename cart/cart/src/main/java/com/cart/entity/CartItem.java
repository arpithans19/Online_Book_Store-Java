package com.cart.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
	
	private int cartItemId;

	private int bookId;
	
	private int userId;

	private double itemTotal;

	private int quantity;
	
	private Book book;
}
