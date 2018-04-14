package emma.store.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity 
@Table(name="Orders")
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="OrderId")
	private int orderId; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OrderDate", nullable = false)
	private Calendar orderDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderDetails> orderDetails;

	public Order(){

	}

	public Order(Calendar orderDate, User user) {
		this.orderDate = orderDate;
		this.user = user;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

}