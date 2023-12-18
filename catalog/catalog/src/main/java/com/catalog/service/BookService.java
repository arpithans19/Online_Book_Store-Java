package com.catalog.service;

import java.util.List;

import com.catalog.entity.Book;

public interface BookService {

	Book saveBook(Book book);

	Book getBookById(int bookId);

	void deleteBookById(int bookId);

	Book modifyBookDetails(Book book);

	int getSequenceNumber(String sequenceName);

	List<Book> getAllBooks();
	
	String generateRandomIsbn();

	List<Book> searchBooksByTitle(String title);

	List<Book> searchBooksByAuthor(String author);

}
