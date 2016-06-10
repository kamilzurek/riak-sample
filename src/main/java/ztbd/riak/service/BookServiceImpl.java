package ztbd.riak.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ztbd.riak.model.Book;
import ztbd.riak.repo.BookRepo;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	@Qualifier("riakBookRepo")
//	@Qualifier("hibernateBookRepo")
	private BookRepo bookRepo;

	@Override
	public void saveBook(Book book) {
		bookRepo.saveBook(book);
	}

	@Override
	public Book getBookById(Object id) {
		return bookRepo.getBookById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.getAllBooks();
	}

	@Override
	public Map<String, Integer> groupByCategory() {
		return bookRepo.groupByCategory();
	}

	@Override
	public Map<String, Integer> groupBySubCategory() {
		return bookRepo.groupBySubCategory();
	}

	@Override
	public Map<String, Integer> groupByYear() {
		return bookRepo.groupByYear();
	}

	@Override
	public Map<String, Integer> groupByRate() {
		return bookRepo.groupByRate();
	}

	@Override
	public List<Book> searchByDescription(String desc) {
		return bookRepo.searchByDescription(desc);
	}
}
