package ztbd.riak.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ztbd.riak.model.Book;
import ztbd.riak.service.BookService;

@Controller
public class RaportRestController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/raport/", method = RequestMethod.GET)
	public ResponseEntity<String> getRaport() {
		String isbn = "798-46-132-465";
		long id = 50L;

		Book book = new Book();
		book.setIsbn(isbn);
//		book.setId(id);
		log.info("Putting book to repo: ");
		try {
//			bookService.saveBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("Getting book: ");

		book = bookService.getBookById(isbn);
//		book = bookService.getBookById(id);

		return new ResponseEntity<String>(book.toString(), HttpStatus.OK);
	}
}
