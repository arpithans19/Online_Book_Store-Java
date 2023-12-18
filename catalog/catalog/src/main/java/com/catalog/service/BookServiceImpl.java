package com.catalog.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.catalog.entity.Book;
import com.catalog.entity.DBSequence;
import com.catalog.exception.BookNotFoundException;
import com.catalog.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public int getSequenceNumber(String sequenceName) {
		// generate sequence no
		Query query = new Query(Criteria.where("id").is(sequenceName));
		// update the sequence no
		Update update = new Update().inc("seq", 1);
		// modify in document
		DBSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DBSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

	@Override
	public String generateRandomIsbn() {
		// Generate a random 13-digit ISBN (excluding checksum)
		
		Random random = new Random();
		StringBuilder isbnBuilder = new StringBuilder("978-0-");

		for (int i = 0; i < 10; i++) {
			isbnBuilder.append(random.nextInt(10));
		}

		return isbnBuilder.toString();
	}

	@Override
	public Book saveBook(Book book) {
		book.setBookId(getSequenceNumber(Book.SEQUENCE_NAME));
		book.setISBN(generateRandomIsbn());
		return bookRepository.save(book);
	}

	@Override
	public Book getBookById(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);

		if (optionalBook.isEmpty())
			throw new BookNotFoundException("Book not found wixth this id: " + bookId);
		return optionalBook.get();
	}

	@Override
	public void deleteBookById(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found wixth this id: " + bookId);

		}
		bookRepository.deleteById(bookId);

	}

	@Override
	public Book modifyBookDetails(Book book) {
		Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found wixth this id" + (book.getBookId()));

		}
		return bookRepository.save(book);

	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			throw new BookNotFoundException("Noo books present");
		}
		return books;
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		return bookRepository.findByTitle(title); // Update method call
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

}
