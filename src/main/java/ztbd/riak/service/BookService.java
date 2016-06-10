package ztbd.riak.service;

import java.util.List;
import java.util.Map;

import ztbd.riak.model.Book;

public interface BookService {

	void saveBook(Book book);

	Book getBookById(Object id);

	List<Book> getAllBooks();

	Map<String, Integer> groupByCategory();

	Map<String, Integer> groupBySubCategory();

	Map<String, Integer> groupByYear();

	Map<String, Integer> groupByRate();

	List<Book> searchByDescription(String desc);
}
