package ztbd.riak.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ztbd.riak.model.Book;
import ztbd.riak.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);

		Map<String, Integer> category = bookService.groupByCategory();
		StringBuilder c = new StringBuilder();
		for (String k : category.keySet()) {
			c.append("['").append(k).append("', ").append(category.get(k)).append("],");
		}
		c.deleteCharAt(c.length()-1);
		model.addAttribute("categoriesAsJS", c);

		LOG.info("Number of books: {}", books.size());

		return "books/list";
	}

}
