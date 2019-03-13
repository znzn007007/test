package cn.deepdraw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book book;

	@Column
	private int count = 1;

	public void increment() {

		this.count++;
	}

	public boolean hasSameBook(Book anotherBook) {

		if (this.book == null) {

			return false;
		}

		return this.book.equals(anotherBook);
	}

	public boolean equals(CartItem item) {

		return this.id != item.getId();
	}

	public CartItem(Book book, int count) {

		this.book = book;
		this.count = count;
	}

	public CartItem(Book book) {

		this.book = book;
	}

	public CartItem() {
	}

	public Book getBook() {

		return book;
	}

	public void setBook(Book book) {

		this.book = book;
	}

	public int getCount() {

		return count;
	}

	public void setCount(int count) {

		this.count = count;
	}
}
