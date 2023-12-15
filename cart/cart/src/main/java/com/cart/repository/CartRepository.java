package com.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart,Integer>{

}
