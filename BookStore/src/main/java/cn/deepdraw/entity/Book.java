package cn.deepdraw.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.deepdraw.utils.DateFormat;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "author")
	private String author;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "publish_date")
	private Date publishDate;

	@Column(name = "introduction")
	private String introduction;

	@Column(name = "cover")
	private String cover;

	@Column(name = "price")
	private BigDecimal price;

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}

	@Override
	public String toString() {

		return "Book [name=" + name + ", author=" + author + ", isbn=" + isbn + ", publishDate=" + publishDate
				+ ", introduction=" + introduction + ", cover=" + cover + ", price=" + price + ", id=" + id + "]";
	}

	public boolean equals(Book book) {

		return this.id == book.getId();
	}

	public Book() {
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAuthor() {

		return author;
	}

	public void setAuthor(String author) {

		this.author = author;
	}

	public String getIsbn() {

		return isbn;
	}

	public void setIsbn(String isbn) {

		this.isbn = isbn;
	}

	public Date getPublishDate() {

		return publishDate;
	}

	public void setPublishDate(String publishDate) {

		this.publishDate = DateFormat.toDate(publishDate);
	}

	public String getIntroduction() {

		return introduction;
	}

	public void setIntroduction(String introduction) {

		this.introduction = introduction;
	}

	public String getCover() {

		return cover;
	}

	public void setCover(String cover) {

		this.cover = cover;
	}

	public static Builder builder() {

		return new Builder();
	}

	public Book(Builder builder) {

		this.author = builder.author;
		this.cover = builder.cover;
		super.id = builder.id;
		this.introduction = builder.introduction;
		this.isbn = builder.isbn;
		this.name = builder.name;
		this.publishDate = builder.publishDate;
		this.price = builder.price;
	}

	public static class Builder {

		private int id;
		private String name;
		private String author;
		private String isbn;
		private Date publishDate;
		private String introduction;
		private String cover;
		private BigDecimal price;

		public void setPrice(BigDecimal price) {

			this.price = price;
		}

		public Book build() {

			return new Book(this);
		}

		public Builder setId(int id) {

			this.id = id;
			return this;
		}

		public Builder setName(String name) {

			this.name = name;
			return this;
		}

		public Builder setAuthor(String author) {

			this.author = author;
			return this;
		}

		public Builder setIsbn(String isbn) {

			this.isbn = isbn;
			return this;
		}

		public Builder setPublishDate(Date publishDate) {

			this.publishDate = publishDate;
			return this;
		}

		public Builder setIntroduction(String introduction) {

			this.introduction = introduction;
			return this;
		}

		public Builder setCover(String cover) {

			this.cover = cover;
			return this;
		}
	}
}
