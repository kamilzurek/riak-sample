package ztbd.riak.repo.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ztbd.riak.model.Book;
import ztbd.riak.repo.AbstractHibernateRepo;
import ztbd.riak.repo.BookRepo;

@Repository("hibernateBookRepo")
public class HibernateBookRepo extends AbstractHibernateRepo<Book> implements BookRepo {

	@Override
	public void saveBook(Book book) {
		persist(book);
	}

	@Override
	public Book getBookById(Object id) {
		Long pk = (Long) id;
		return getById(pk);
	}

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	public Map<String, Integer> groupByCategory() {
		return null;
	}

}
