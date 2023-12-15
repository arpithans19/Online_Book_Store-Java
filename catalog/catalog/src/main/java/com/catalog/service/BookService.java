package com.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catalog.entity.*;


public interface BookService {

	Book saveBook(Book book);

	Book getBookById(int bookId);

	void deleteBookById(int bookId);

	Book searchBook(String bookTitle);

	Book modifyBookDetails(Book book);

	int getSequenceNumber(String sequenceName);

	List<Book> getAllBooks();


}
