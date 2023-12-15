package com.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.entity.Book;
import com.catalog.service.BookService;

@RestController
@RequestMapping("/catalog")

public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/getallbooks")
	public List<Book> fetchAllBooks() {
		List<Book> booksList = bookService.getAllBooks();
		return booksList;

	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@Validated @RequestBody Book book) {
		Book newBook = bookService.saveBook(book);
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(newBook, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<String> removeBook(@PathVariable("bookId") int bookId) {
		bookService.deleteBookById(bookId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Book deleted", HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("/get/bookId")
	public ResponseEntity<Object> getBookById(@PathVariable("bookId") int bookId) {
		ResponseEntity<Object> responseEntity = null;
		Book book = bookService.getBookById(bookId);
		responseEntity = new ResponseEntity<>(book, HttpStatus.OK);
		return responseEntity;

	}

	@PutMapping("/update")
	public ResponseEntity<Book> updateBookDetails(@RequestBody Book book) {
		Book updateBook = bookService.modifyBookDetails(book);
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(updateBook, HttpStatus.OK);
		return responseEntity;

	}

}
