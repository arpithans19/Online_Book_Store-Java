package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.entity.Cart;
import com.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/get/{cartId}")
	public ResponseEntity<Object> fetchCartById(@PathVariable("cartId") int cartId) {

		ResponseEntity<Object> responseEntity = null;
		Cart cart = cartService.getCartById(cartId);
		responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/addtoCart")
	public ResponseEntity<Cart> addFlight(@Validated @RequestBody Cart cart) {
		Cart newCart = cartService.addItemToCart(cart);
		ResponseEntity<Cart> responseEntity = new ResponseEntity<>(newCart, HttpStatus.OK);
		return responseEntity;

	}

	@PutMapping("/update")
	public ResponseEntity<Cart> modifyCart(@RequestBody Cart cart) {
		Cart updateCart = cartService.updateCart(cart);
		ResponseEntity<Cart> responseEntity = new ResponseEntity<>(updateCart, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<String> removeItem(@PathVariable("cartId") int cartId) {
		cartService.removeItemFromCart(cartId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("item deleted successfully from cart!!",
				HttpStatus.OK);
		return responseEntity;
	}

}
