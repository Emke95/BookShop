package emma.store.entity;

import java.util.Calendar;

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
@Table(name = "OrderDetails")
public class OrderDetails {

	@Id
	@Column(name = "OrderDetailsId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderDetailsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderDetailsDate", nullable = false)
	private Calendar orderDate;

	@Column(name = "orderDetailsQuantity", nullable = false)
	private int quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookId")
	private Book book;

	public OrderDetails(){

	}

	public OrderDetails(Calendar orderDate, int quantity, Book book) {
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.book = book;
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderDetailsId;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + quantity;
		return result;
	}
}
