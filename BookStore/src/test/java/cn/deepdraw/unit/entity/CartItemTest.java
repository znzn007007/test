package cn.deepdraw.unit.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.CartItem;

public class CartItemTest {

	@Test
	public void increment_happyPath_whenCountNotOne() {

		CartItem item = new CartItem();
		item.setCount(4);
		item.increment();

		assertEquals(5, item.getCount());
	}

	@Test
	public void increment_happyPath_whenCountIsOne() {

		CartItem item = new CartItem();
		item.increment();

		assertEquals(2, item.getCount());
	}

	@Test
	public void hasSameBook_happyPath() {

		CartItem item = new CartItem();
		item.setBook(createBook(1));
		Book book = createBook(1);

		assertTrue(item.hasSameBook(book));
	}

	@Test
	public void hasSameBook_shouldBeFalse_whenNotSame() {

		CartItem item = new CartItem();
		item.setBook(createBook(1));
		Book book2 = createBook(2);

		assertFalse(item.hasSameBook(book2));
	}

	@Test
	public void hasSameBook_shouldBeFalse_whenItemIsNull() {

		CartItem item = new CartItem();
		Book book2 = createBook(2);

		assertFalse(item.hasSameBook(book2));
	}

	private Book createBook(int id) {

		return Book.builder().setId(id).build();
	}
}
