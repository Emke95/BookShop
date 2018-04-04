package emma.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="Book")
public class Book {

	@Id
	@Column(name="Isbn", nullable = false)
	private String isbn;

	@Column(name="Title", nullable=false)
	private String title;

	@Column(name="Author", nullable=false)
	private String author;


	@Column(name="Price", nullable=false)
	private double price;
			
			@Enumerated(EnumType.STRING)
			@Column(name = "Category")
			private Category category;

	//@Column(name="Category", nullable=false)
	//private String category;

	@Column(name="Image")
	@Lob
	private byte[] image;

	@Column (name="Quantity")
	private long quantity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateAdded", nullable = false) 
	private Date createDate;
	
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


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}