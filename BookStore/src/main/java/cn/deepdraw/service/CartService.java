package cn.deepdraw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepdraw.dao.CartDao;
import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.User;

@Service
public class CartService {

	@Autowired
	private CartDao dao;

	public Cart getByUser(User user) {

		Cart cart = dao.getByUserId(user.getId());
		return cart == null ? create(user) : cart;
	}

	private Cart create(User user) {

		Cart cart = new Cart(user);
		dao.save(cart);
		return cart;
	}

	public void add(Cart cart, Book book) {

		cart.addItem(book);
		dao.update(cart);
	}

	public void update(Cart cart, Book book, Integer count) {

		cart.updateItem(book, count);
		dao.update(cart);
	}

	public void delete(Cart cart, Book book) {

		int itemId = cart.getItem(book).getId();
		cart.deleteItem(book);
		dao.update(cart);
		dao.deleteItem(itemId);
	}
}
