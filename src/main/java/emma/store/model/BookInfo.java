package emma.store.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import emma.store.entity.Book;

public class BookInfo {

	private String isbn;
	private String title;
	private String author;
	private String category;
	private double price;
	private long quantity;
	private CommonsMultipartFile fileData;
	private boolean newBook=false;

	public BookInfo() {

	}

	public BookInfo(Book book) {
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.price = book.getPrice();
		this.quantity = book.getQuantity();
	}

	public BookInfo(String isbn, String title, String author, String category, double price, long quantity) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}



	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public boolean isNewBook() {
		return newBook;
	}

	public void setNewBook(boolean newBook) {
		this.newBook = newBook;
	}


}
