package cn.deepdraw.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<CartItem> items = new ArrayList<>();

	public void addItem(Book book) {

		CartItem item = getItem(book);
		if (item != null) {

			item.increment();
		} else {

			this.items.add(new CartItem(book));
		}
	}

	public void deleteItem(Book book) {

		CartItem item = getItem(book);
		if (item != null) {

			items.remove(item);
		}
	}

	public void updateItem(Book book, Integer count) {

		CartItem item = getItem(book);
		if (item != null) {

			item.setCount(count);
		} else {

			CartItem newItem = new CartItem(book, count);
			items.add(newItem);
		}
	}

	public CartItem getItem(Book book) {

		return items.stream().filter(item -> item.hasSameBook(book)).findFirst().orElse(null);
	}

	public Cart() {
	}

	public Cart(User user) {

		this.user = user;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public List<CartItem> getItems() {

		return items;
	}

	public void setItems(List<CartItem> items) {

		this.items = items;
	}
}
