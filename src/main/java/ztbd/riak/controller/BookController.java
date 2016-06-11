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

	@RequestMapping(value = "/raport", method = RequestMethod.GET)
	public String list(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);

		Map<String, Integer> group = bookService.groupByCategory();
		model.addAttribute("categoriesAsJS", getAsJson(group));
		model.addAttribute("categories", group);

		group = bookService.groupBySubCategory();
		model.addAttribute("subcategoriesAsJS", getAsJson(group));

		group = bookService.groupByYear();
		model.addAttribute("yearsAsJS", getAsJson(group));

		group = bookService.groupByRate();
		model.addAttribute("ratesAsJS", getAsJson(group));

		LOG.info("Search: {}", bookService.searchByDescription("Microsoft"));

//		LOG.info("Number of books: {}", books.size());

		return "books/list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		return "books/search";
	}

	private String getAsJson(Map<String, Integer> group) {
		StringBuilder c = new StringBuilder();
		for (String k : group.keySet()) {
			c.append("['").append(k).append("', ").append(group.get(k)).append("],");
		}
		if (c.length() > 0) {
			c.deleteCharAt(c.length() - 1);
		}
		return c.toString();
	}

}
