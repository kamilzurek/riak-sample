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

		report.setGroupByCategory( bookService.groupByCategory() );
		report.setGroupBySubCategory( bookService.groupBySubCategory() );
		report.setGroupByYears( bookService.groupByYear() );
		report.setGroupByRatings( bookService.groupByRate() );

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SearchBookDto>> search(@RequestParam("t") String term) {
		List<Book> books = bookService.searchByDescription(term);
		List<SearchBookDto> result = new ArrayList<>(books.size());

		for (Book book : books) {
			result.add( new SearchBookDto(book.getTitle(), book.getId(), book.getIsbn()) );
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
