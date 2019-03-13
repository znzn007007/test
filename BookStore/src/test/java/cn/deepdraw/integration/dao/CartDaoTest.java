package cn.deepdraw.integration.dao;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.deepdraw.dao.BookDao;
import cn.deepdraw.dao.CartDao;
import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:TestSpring.xml")
public class CartDaoTest {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private BookDao bookDao;

	@Test
	public void save_happyPath() {

		Cart cart = new Cart();
		cartDao.save(cart);

		assertNotNull(cart.getId());
	}

	@Test
	public void getByUserId_happyPath() {

		Cart cart = new Cart();
		User user = new User();
		cart.setUser(user);
		cartDao.save(cart);

		assertNotNull(cartDao.getByUserId(1));
	}

	@Test
	public void getByUserId_shouldBeNull_whenNoUser() {

		Cart cart = new Cart();
		cartDao.save(cart);

		assertNull(cartDao.getByUserId(1));
	}

	@Test
	public void deleteItem_happyPath() {

		Cart cart = new Cart(new User());
		Book book = new Book();

		bookDao.save(book);
		cart.addItem(book);
		cartDao.save(cart);
		cart.deleteItem(book);
		cartDao.update(cart);
		cartDao.deleteItem(cart.getId());

		assertThat(cartDao.get(Cart.class, 1).getItems(), is(empty()));
	}
}
