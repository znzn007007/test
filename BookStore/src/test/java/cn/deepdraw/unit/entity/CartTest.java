package cn.deepdraw.unit.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.CartItem;

public class CartTest {

	@Test
	public void getItem_happyPath() {

		Book book = new Book();
		CartItem item = new CartItem(book);
		Cart cart = getCart(item);

		assertEquals(item, cart.getItem(book));
	}

	@Test
	public void getItem_shouldBeNull_whenNoSuchBook() {

		Book book = createBook(1);
		CartItem item = new CartItem(createBook(2));
		Cart cart = getCart(item);

		assertNull(cart.getItem(book));
	}

	@Test
	public void addItem_shouldDoIncrement_whenBookIsExist() {

		Book book = new Book();
		CartItem item = new CartItem(book);
		Cart cart = getCart(item);
		cart.addItem(book);

		assertEquals(2, cart.getItem(book).getCount());
	}

	@Test
	public void addItem_shouldAddNewItem_whenBookIsNotExist() {

		Book book = new Book();
		Cart cart = new Cart();
		cart.addItem(book);

		assertNotNull(cart.getItem(book));
	}

	@Test
	public void updateItem_shouldSetCount_whenBookExist() {

		Book book = new Book();
		Cart cart = getCart(new CartItem(book));
		cart.updateItem(book, 3);

		assertEquals(3, cart.getItem(book).getCount());
	}

	@Test
	public void updateItem_shouldAddNewItem_whenBookIsNotExist() {

		Book book = new Book();
		Cart cart = new Cart();
		cart.setItems(new ArrayList<CartItem>());
		cart.updateItem(book, 3);

		assertEquals(3, cart.getItem(book).getCount());
	}

	@Test
	public void deleteItem_happyPath() {

		Book book = new Book();
		Cart cart = getCart(new CartItem(book));
		cart.deleteItem(book);

		assertThat(cart.getItems(), is(empty()));
	}

	@Test
	public void deleteItem_shouldNotDelete_whenNoThatBook() {

		Book book2 = createBook(2);
		Cart cart = getCart(new CartItem(createBook(1)));
		cart.deleteItem(book2);

		assertThat(cart.getItems(), not(empty()));
	}

	private Book createBook(int id) {

		return Book.builder().setId(id).build();
	}

	private Cart getCart(CartItem item) {

		Cart cart = new Cart();
		cart.getItems().add(item);
		return cart;
	}
}
