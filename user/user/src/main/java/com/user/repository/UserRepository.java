package com.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.user.entity.User;

public interface UserRepository extends MongoRepository<User, Integer>{

	@Query("{name:?0}")
	Optional<User> findByUserName(String name);

	//Find entities where the 'phone' field matches 
	//the value of the first parameter passed to the method
	//List<EntityType> findByPhone(String phoneNumber);

	@Query("{phone:?0}")
	Optional<User> findByPhone(long phone);

	@Query("{userName:?0}")
	Optional<User> findByName(String userName);
}
