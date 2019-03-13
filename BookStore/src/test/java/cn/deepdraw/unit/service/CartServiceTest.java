package cn.deepdraw.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cn.deepdraw.dao.CartDao;
import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.CartItem;
import cn.deepdraw.entity.User;
import cn.deepdraw.service.CartService;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

	@Mock
	private CartDao dao;

	@Mock
	private Cart cart;

	@Mock
	private User user;

	@Mock
	private Book book;

	@Mock
	private CartItem item;

	@InjectMocks
	private CartService service;

	@Test
	public void getByUser_happyPath_whenCartIsNotNull() {

		when(user.getId()).thenReturn(1);
		when(dao.getByUserId(anyInt())).thenReturn(cart);

		assertEquals(cart, service.getByUser(user));
	}

	@Test
	public void getByUser_happyPath_whenCartIsNull() {

		when(user.getId()).thenReturn(1);
		when(dao.getByUserId(anyInt())).thenReturn(null);
		when(dao.getByUserId(1)).thenReturn(cart);

		assertEquals(cart, service.getByUser(user));
	}

	@Test
	public void add_happyPath() {

		service.add(cart, book);

		verify(cart).addItem(book);
		verify(dao).update(cart);
	}

	@Test
	public void update_happyPath() {

		service.update(cart, book, 1);

		verify(cart).updateItem(book, 1);
		verify(dao).update(cart);
	}

	@Test
	public void deleteItem_happyPath() {

		when(cart.getItem(book)).thenReturn(item);
		service.delete(cart, book);

		verify(cart).deleteItem(book);
		verify(dao).update(cart);
		verify(dao).deleteItem(anyInt());
	}
}
