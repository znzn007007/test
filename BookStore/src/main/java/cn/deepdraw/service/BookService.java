package cn.deepdraw.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.deepdraw.bean.Page;
import cn.deepdraw.dao.BookDao;
import cn.deepdraw.entity.Book;
import cn.deepdraw.utils.FileUtils;

@Service
public class BookService {

	@Autowired
	private BookDao dao;

	@Autowired
	private FileUtils helper;

	public void save(Book book, MultipartFile uploadFile) {

		String imgName = helper.saveImg(uploadFile);

		book.setCover(imgName);
		dao.save(book);
	}

	public void update(Book book, MultipartFile uploadFile) {

		String imgName = helper.saveImg(uploadFile);

		book.setCover(imgName);
		dao.update(book);
	}

	public void delete(int id) {

		dao.delete(dao.get(Book.class, id));
	}

	public Book get(int id) {

		return dao.get(Book.class, id);
	}

	public List<Book> search(String goal, String keyword, Page page) {

		int count = dao.countBySearch(goal, keyword).intValue();
		if (count > 0) {

			page.setTotalNum(count);
			List<Book> books = dao.search(goal, keyword, page);
			return books;
		}
		return Collections.emptyList();
	}

	public List<Book> getBooks(Page page) {

		int count = dao.count().intValue();
		if (count > 0) {

			page.setTotalNum(count);
			return dao.getBooks(page);
		}
		return Collections.emptyList();
	}
}
