package com.cart.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cart.entity.Cart;
import com.cart.entity.DBSequence;
import com.cart.exception.CartNotFoundException;

import com.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepo; 
	
	@Autowired
	private MongoOperations mongoOperations;
	
	
	@Override
	public Cart addItemToCart(Cart cart) {
		cart.setCartId(getSequenceNumber(Cart.SEQUENCE_NAME));
//		Cart savedCart =cartRepo.save(cart);
		return cartRepo.save(cart);
	}

	@Override
	public void removeItemFromCart(int cartId) {
		Optional<Cart> optionalCart = cartRepo.findById(cartId);
		if (optionalCart.isEmpty()) {
			throw new CartNotFoundException("Cart Not existing with Id: "+cartId);
		}
		cartRepo.deleteById(cartId);
	}

	@Override
	public Cart updateCart(Cart cart) {
		Optional<Cart> optionalCart = cartRepo.findById(cart.getCartId());
		if (optionalCart.isEmpty()) {
			throw new CartNotFoundException("Cart Not existing with Id: "+cart.getCartId());
		}
		return cartRepo.save(cart);
	}

	@Override
	public Cart getCartById(int cartId) {
		Optional<Cart> optionalCart = cartRepo.findById(cartId);
		if (optionalCart.isEmpty()) {
			throw new CartNotFoundException("Cart Not existing with Id: "+cartId);
		}
		return optionalCart.get();
	}

	@Override
	public int getSequenceNumber(String sequenceName) {
		//generate sequence no
				Query query=new Query(Criteria.where("id").is(sequenceName));
				//update the sequence no
				Update update=new Update().inc("seq",1);
				//modify in document
				DBSequence counter=mongoOperations.findAndModify(query,update, options().returnNew(true).upsert(true),DBSequence.class);
				return !Objects.isNull(counter)? counter.getSeq():1;
	}
	
	
	

}
