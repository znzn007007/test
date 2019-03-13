package cn.deepdraw.integration.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.deepdraw.bean.Page;
import cn.deepdraw.dao.BookDao;
import cn.deepdraw.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:TestSpring.xml")
public class BookDaoTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void update_happyPath() {
		Book book = Book.builder().setName("aaa").setAuthor("bbb").build();
		bookDao.save(book);
		book.setName("ccc");
		System.out.println(book);
		bookDao.update(book);

		assertEquals("ccc", bookDao.get(Book.class, 1).getName());
	}

	@Test
	public void delete_happyPath() {
		Book book = Book.builder().setName("aaa").setAuthor("bbb").build();

		bookDao.save(book);
		bookDao.delete(Book.builder().setId(1).build());

		assertEquals(null, bookDao.get(Book.class, 1));
	}

	@Test
	public void getBooks_happyPath() {

		setBooks();

		assertEquals(3, bookDao.count().intValue());
		Page page = new Page(1, 2, bookDao.count().intValue());
		List<Book> books = bookDao.getBooks(page);

		assertEquals("bbb", books.get(1).getName());
		assertEquals(2, books.size());
	}

	@Test
	public void getBooks_shouldBeNull_whenNoBook() {

		Page page = new Page(1, 2, bookDao.count().intValue());
		List<Book> books = bookDao.getBooks(page);

		assertEquals(0, books.size());
	}

	@Test
	public void count_happyPath() {

		Book book = new Book();
		bookDao.save(book);

		assertEquals(new Long(1L), bookDao.count());
	}

	@Test
	public void count_shouldBe0_whenNoBook() {

		assertEquals(new Long(0L), bookDao.count());
	}

	@Test
	public void countBySearch_happyPath() {

		Book book = Book.builder().setName("流浪地球").build();
		bookDao.save(book);

		assertEquals(new Long(1L), bookDao.countBySearch("name", "流"));
	}

	@Test
	public void countBySearch_shouldBe0_whenNoBook() {

		assertEquals(new Long(0L), bookDao.countBySearch("name", "流"));
	}

	@Test
	public void search_happyPath() {

		setBooks();

		Page page = new Page(1, 2, bookDao.count().intValue());
		List<Book> books = bookDao.search("name", "流浪", page);

		assertEquals(1, books.size());
	}

	@Test
	public void search_shouldBeNull_whenNoBook() {

		Page page = new Page(1, 2, bookDao.count().intValue());
		List<Book> books = bookDao.search("name", "流浪", page);

		assertEquals(0, books.size());
	}

	private List<Book> setBooks() {

		Book book1 = Book.builder().setName("流浪地球").build();
		Book book2 = Book.builder().setName("bbb").build();
		Book book3 = Book.builder().setName("ccc").build();
		bookDao.save(book1);
		bookDao.save(book2);
		bookDao.save(book3);

		List<Book> list = new ArrayList<>();
		list.add(book1);
		list.add(book2);
		list.add(book3);
		return list;
	}

}
