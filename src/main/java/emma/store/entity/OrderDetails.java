package emma.store.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "orderdetails")
public class OrderDetails {

	@Id
	@Column(name = "OrderDetailsId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "OrderDetailsDate", nullable = false)
	private Date orderDate;

	@Column(name = "OrderDetailsQuantity", nullable = false)
	private int quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private Orders orders;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderDemoId")
	private OrderDemo orderDemos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookId")
	private Book book;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}


	public OrderDetails(){

	}

	public OrderDetails(Date orderDate, int quantity, Book book) {
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.book = book;
	}

	public OrderDemo getOrderDemos() {
		return orderDemos;
	}

	public void setOrderDemos(OrderDemo orderDemos) {
		this.orderDemos = orderDemos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderDemos == null) ? 0 : orderDemos.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = (int) (prime * result + id);
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		if (orderDemos == null) {
			if (other.orderDemos != null)
				return false;
		} else if (!orderDemos.equals(other.orderDemos))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (id != other.id)
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}