package com.cart.service;

import com.cart.entity.Cart;

public interface CartService {
	
	Cart addItemToCart(Cart cart);
	void removeItemFromCart(int cartId);
	Cart updateCart(Cart cart);
	Cart getCartById(int cartId);
	int getSequenceNumber(String sequenceName);
	
	

}
