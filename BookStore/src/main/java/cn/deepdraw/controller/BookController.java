package cn.deepdraw.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.deepdraw.bean.Page;
import cn.deepdraw.entity.Book;
import cn.deepdraw.service.BookService;

@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookService service;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd() {

		return "book/add-book";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String toUpdate(int id, ModelMap model) {

		Book book = service.get(id);

		model.put("book", book);
		return "book/update-book";
	}

	@RequestMapping(value = "/shelf", method = RequestMethod.GET)
	public String bookShelf(@RequestParam(defaultValue = "1") int pageNo, ModelMap model) {

		Page page = new Page(pageNo);
		List<Book> books = service.getBooks(page);

		model.put("books", books);
		model.put("page", page);
		model.put("req", "shelf");
		return "book/book-shelf";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "coverImg") MultipartFile uploadFile, Book book) {

		service.save(book, uploadFile);
		return "redirect:shelf";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "coverImg") MultipartFile uploadFile, Book book) {

		service.update(book, uploadFile);
		return "redirect:shelf";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int id) {

		service.delete(id);
		return "redirect:shelf";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(defaultValue = "1") int pageNo, String goal, String keyword, ModelMap model) {

		if (StringUtils.isBlank(keyword)) {

			return "redirect:shelf";
		}

		Page page = new Page(pageNo);
		List<Book> books = service.search(goal, keyword, page);

		model.put("books", books);
		model.put("page", page);
		model.put("keyword", keyword);
		model.put("goal", goal);
		model.put("req", "search");
		return "book/book-shelf";
	}
}
