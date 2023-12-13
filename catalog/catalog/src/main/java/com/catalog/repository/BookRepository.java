package com.catalog.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.catalog.entity.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer>{

	@Query("{title:?0}")
	Optional<Book> findBookByTitle(String bookTitle);

	

	
	
	
	
	
	
}
