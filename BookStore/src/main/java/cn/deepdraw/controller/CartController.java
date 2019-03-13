package cn.deepdraw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.deepdraw.entity.Book;
import cn.deepdraw.entity.Cart;
import cn.deepdraw.entity.User;
import cn.deepdraw.service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService service;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toCart(ModelMap model) {

		Cart cart = getCart();

		model.put("cart", cart);
		return "cart/my-cart";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Book book) {

		Cart cart = getCart();

		service.add(cart, book);
		return "redirect:/cart/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Book book, Integer count) {

		Cart cart = getCart();

		service.update(cart, book, count);
		return "redirect:/cart/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Book book) {

		Cart cart = getCart();

		service.delete(cart, book);
		return "redirect:/cart/";
	}

	private Cart getCart() {

		User user = (User) session.getAttribute("user");
		return service.getByUser(user);
	}
}
