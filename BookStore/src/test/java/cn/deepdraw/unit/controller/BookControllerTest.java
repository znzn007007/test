package cn.deepdraw.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import cn.deepdraw.bean.Page;
import cn.deepdraw.controller.BookController;
import cn.deepdraw.entity.Book;
import cn.deepdraw.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	private Book book;

	@Mock
	private Page page;

	@Mock
	private ModelMap model;

	@Mock
	private BookService service;

	@InjectMocks
	private BookController controller;

	private MockMvc mockMvc;

	@Before
	public void initMokcMvc() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void toAdd_happyPath() throws Exception {

		mockMvc.perform(get("/book/add")).andExpect(status().isOk()).andExpect(forwardedUrl("book/add-book"))
				.andDo(print()).andReturn();
	}

	@Test
	public void toUpdate_happyPath_mvc() throws Exception {

		when(service.get(anyInt())).thenReturn(book);

		mockMvc.perform(get("/book/update").param("id", new Integer(anyInt()).toString())).andExpect(status().isOk())
				.andExpect(forwardedUrl("book/update-book")).andExpect(model().attribute("book", book)).andDo(print())
				.andReturn();

	}

	@Test
	public void toUpdate_happyPath() throws Exception {

		when(service.get(1)).thenReturn(book);

		assertEquals("book/update-book", controller.toUpdate(1, model));
		verify(model).put("book", book);
	}

	// 111
	@Test
	public void bookShelf_happyPath_mvc() throws Exception {

		Page page = new Page(1);
		List<Book> books = new ArrayList<Book>();
		when(service.getBooks(page)).thenReturn(books);

		mockMvc.perform(get("/book/shelf").param("id", "1")).andExpect(status().isOk())
				.andExpect(forwardedUrl("book/book-shelf")).andExpect(model().attribute("books", books))
				.andExpect(model().attribute("req", "shelf")).andExpect(model().attribute("page", page)).andDo(print())
				.andReturn();
	}

	@Test
	public void bookShelf_happyPath() {

		List<Book> books = new ArrayList<Book>();
		Page page = new Page(1);
		when(service.getBooks(page)).thenReturn(books);

		assertEquals("book/book-shelf", controller.bookShelf(1, model));
		verify(model).put("books", books);
		verify(model).put("page", page);
		verify(model).put("req", "shelf");
	}

	@Test
	public void add_happyPath_mvc() throws Exception {

		MockMultipartFile cover = new MockMultipartFile("coverImg", "a.gif", "image/gif", "gif".getBytes());

		mockMvc.perform(
				multipart("/book/add").file(cover).param("name", "name").contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("shelf")).andDo(print()).andReturn();
	}

	@Test
	public void add_happyPath() {

		MultipartFile uploadFile = new MockMultipartFile("name", "img.gif".getBytes());
		assertEquals("redirect:shelf", controller.add(uploadFile, book));
		verify(service).save(book, uploadFile);
	}

	@Test
	public void update_happyPath_mvc() throws Exception {

		MockMultipartFile cover = new MockMultipartFile("coverImg", "a.gif", "image/gif", "gif".getBytes());

		mockMvc.perform(multipart("/book/update").file(cover).param("name", "name").param("author", "author")
				.contentType(MediaType.MULTIPART_FORM_DATA)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("shelf")).andDo(print()).andReturn();
	}

	@Test
	public void update_happyPath() {

		MultipartFile uploadFile = new MockMultipartFile("name", "img.gif".getBytes());

		assertEquals("redirect:shelf", controller.update(uploadFile, book));
		verify(service).update(book, uploadFile);
	}

	@Test
	public void delete_happyPath_mvc() throws Exception {

		mockMvc.perform(get("/book/delete").param("id", "1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("shelf")).andDo(print()).andReturn();
	}

	@Test
	public void delete_happyPath() {

		assertEquals("redirect:shelf", controller.delete(1));
		verify(service).delete(1);
	}

	@Test
	public void search_happyPath_mvc() throws Exception {

		Page page = new Page(1);
		List<Book> books = new ArrayList<>();

		mockMvc.perform(get("/book/search").param("pageNo", "1").param("goal", "goal").param("keyword", "text"))
				.andExpect(model().attribute("books", books)).andExpect(model().attribute("page", page))
				.andExpect(model().attribute("keyword", "text")).andExpect(model().attribute("goal", "goal"))
				.andExpect(model().attribute("req", "search")).andExpect(status().isOk())
				.andExpect(forwardedUrl("book/book-shelf")).andDo(print()).andReturn();
	}

	@Test
	public void search_happyPath() {

		Page page = new Page(1);
		List<Book> books = new ArrayList<>();

		assertEquals("book/book-shelf", controller.search(1, "goal", "text", model));
		verify(model).put("books", books);
		verify(model).put("page", page);
		verify(model).put("keyword", "text");
		verify(model).put("goal", "goal");
		verify(model).put("req", "search");
	}

	@Test
	public void search_shouldToShelf_whenNotSearch_() throws Exception {

		mockMvc.perform(get("/book/search").param("pageNo", "1").param("goal", "goal").param("text", ""))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("shelf")).andDo(print()).andReturn();
	}

	@Test
	public void search_shouldToShelf_whenNotSearch() {

		assertEquals("redirect:shelf", controller.search(1, "goal", "", model));
	}
}
