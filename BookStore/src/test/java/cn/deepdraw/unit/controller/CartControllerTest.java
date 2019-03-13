package cn.deepdraw.unit.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import cn.deepdraw.controller.CartController;
import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.CartItem;
import cn.deepdraw.entity.User;
import cn.deepdraw.service.CartService;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

	@Mock
	private CartService service;

	@Mock
	private HttpSession session;

	@Mock
	private User user;

	@Mock
	private Book book;

	@Mock
	private ModelMap model;

	@Mock
	private CartItem item;

	@Mock
	private Cart cart;

	@InjectMocks
	private CartController controller;

	MockMvc mockMvc;

	@Before
	public void initMokcMvc() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void toCart_happyPath_mvc() throws Exception {

		when(session.getAttribute(any())).thenReturn(user);
		when(service.getByUser(user)).thenReturn(cart);

		mockMvc.perform(get("/cart/")).andExpect(status().isOk()).andExpect(model().attribute("cart", cart))
				.andExpect(forwardedUrl("cart/my-cart")).andDo(print()).andReturn();
	}

	@Test
	public void toCart_happyPath() {

		when(session.getAttribute("user")).thenReturn(user);
		when(service.getByUser(user)).thenReturn(cart);

		assertEquals("cart/my-cart", controller.toCart(model));
		verify(model).put("cart", cart);
		ModelResultMatchers model2 = MockMvcResultMatchers.model();
		System.out.println(model2.attributeExists("cart"));
	}

	@Test
	public void add_get_happyPath_mvc() throws Exception {

		mockMvc.perform(get("/cart/add").param("name", "name")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/cart/")).andDo(print()).andReturn();
	}

	@Test
	public void add_happyPath() {

		when(session.getAttribute("user")).thenReturn(user);
		when(service.getByUser(user)).thenReturn(cart);

		assertEquals("redirect:/cart/", controller.add(book));
		verify(service).add(cart, book);
	}

	@Test
	public void update_happyPath_mvc() throws Exception {

		mockMvc.perform(get("/cart/update").param("name", "name").param("count", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/cart/")).andDo(print()).andReturn();
	}

	@Test
	public void update_happyPath() {

		when(session.getAttribute("user")).thenReturn(user);
		when(service.getByUser(user)).thenReturn(cart);

		assertEquals("redirect:/cart/", controller.update(book, 3));
		verify(service).update(cart, book, 3);
	}

	@Test
	public void delete_happyPath_mvc() throws Exception {

		mockMvc.perform(get("/cart/delete").param("name", "name")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/cart/")).andDo(print()).andReturn();
	}

	@Test
	public void delete_happyPath() {

		when(session.getAttribute("user")).thenReturn(user);
		when(service.getByUser(user)).thenReturn(cart);

		assertEquals("redirect:/cart/", controller.delete(book));
		verify(service).delete(cart, book);
	}
}
