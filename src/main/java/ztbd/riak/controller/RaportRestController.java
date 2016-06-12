package ztbd.riak.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ztbd.riak.domain.DetailsBookDto;
import ztbd.riak.domain.ReportDto;
import ztbd.riak.domain.SearchBookDto;
import ztbd.riak.model.Book;
import ztbd.riak.service.BookService;

@Controller
public class RaportRestController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ReportDto> getRaport() {
		ReportDto report = new ReportDto();

		long st = System.currentTimeMillis();
		report.setGroupByCategory( bookService.groupByCategory() );
		report.setGroupBySubCategory( bookService.groupBySubCategory() );
		report.setGroupByYears( bookService.groupByYear() );
		report.setGroupByRatings( bookService.groupByRate() );
		long end = System.currentTimeMillis();
		System.out.println("t: " + (end - st));

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SearchBookDto>> search(@RequestParam("t") String term) {
		long st = System.currentTimeMillis();
		List<Book> books = bookService.searchByDescription(term);
		long end = System.currentTimeMillis();
		System.out.println("t: " + (end - st));
		List<SearchBookDto> result = new ArrayList<>(books.size());

		for (Book book : books) {
			result.add( new SearchBookDto(book.getTitle(), book.getId(), book.getIsbn()) );
		}

//		log.info("Book searched size: {}", books.size());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DetailsBookDto> details(@RequestParam("id") Long id) {
		long st = System.currentTimeMillis();
		Book book = bookService.getBookById(id);
		long end = System.currentTimeMillis();
		System.out.println("t: " + (end - st));
		DetailsBookDto dto;
		if (book != null) {
			dto = new DetailsBookDto(book.getTitle(), book.getAuthor(), book.getDesc() != null ? book.getDesc() : book.getDesc_s());
		} else {
			dto = new DetailsBookDto();
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
