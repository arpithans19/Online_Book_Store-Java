package com.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.catalog.entity.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
	List<Book> findByTitle(String title); 
    List<Book> findByAuthor(String author);

}
