package cn.deepdraw.unit.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import cn.deepdraw.bean.Page;
import cn.deepdraw.dao.BookDao;
import cn.deepdraw.entity.Book;
import cn.deepdraw.service.BookService;
import cn.deepdraw.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	private BookDao dao;

	@Mock
	private MultipartFile uploadFile;

	@Mock
	private FileUtils helper;

	@Mock
	private Book book;

	@Mock
	private Page page;

	@InjectMocks
	private BookService service;

	@Test
	public void save_happyPath() {

		when(helper.saveImg(uploadFile)).thenReturn("path");

		service.save(book, uploadFile);
		verify(dao).save(book);
	}

	@Test
	public void update_happyPath() {

		when(helper.saveImg(uploadFile)).thenReturn("path");

		service.update(book, uploadFile);
		verify(dao).update(book);
	}

	@Test
	public void delete_happyPath() {

		when(dao.get(Book.class, 1)).thenReturn(book);
		service.delete(1);
		verify(dao).delete(book);
	}

	@Test
	public void get_happyPath() {

		when(dao.get(Book.class, 1)).thenReturn(book);
		assertEquals(book, service.get(1));
	}

	@Test
	public void search_happyPath() {

		Page page = mock(Page.class);
		List<Book> books = mock(List.class);

		when(dao.countBySearch("goal", "text")).thenReturn(1L);
		page.setTotalNum(1);
		when(dao.search("goal", "text", page)).thenReturn(books);

		verify(page).setTotalNum(1);
		assertEquals(books, service.search("goal", "text", page));
	}

	@Test
	public void search_shouldBeEmpty_ifCountIsZero() {

		when(dao.countBySearch("goal", "text")).thenReturn(0L);

		List<Book> books = service.search("goal", "text", page);
		assertThat(books, is(empty()));
	}

	@Test
	public void getBooks_happyPath() {

		Page page = mock(Page.class);
		List<Book> books = mock(List.class);

		when(dao.count()).thenReturn(1L);
		page.setTotalNum(1);
		when(dao.getBooks(page)).thenReturn(books);

		verify(page).setTotalNum(1);
		assertEquals(books, service.getBooks(page));
	}

	@Test
	public void getBooks_shouldBeEmpty_ifCountIsZero() {

		when(dao.count()).thenReturn(0L);
		List<Book> books = service.getBooks(page);
		assertThat(books, is(empty()));
	}

}
