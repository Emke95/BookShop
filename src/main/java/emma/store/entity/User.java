package emma.store.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;


@Entity
@Table(name = "User")
public class User {

	public enum Role {
		USER, ADMIN
	}

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId")
	private long id;

	@Column(name = "FirstName", nullable=false)
	private String firstName;

	@Column(name = "LastName", nullable=false)
	private String lastName;

	@Column(name = "Email", nullable=false, unique= true)
	@Email
	private String email;

	@Column(name="Active")
	private boolean active;

	@Column(name="Password", nullable=false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role = Role.USER;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private ShoppingCart shoppingCart;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Orders> orderList;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserShipping> userShippingList;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserPayment> userPaymentList;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<OrderDemo> orderDemos;
	
		public String getFirstName() {
		return firstName;
	}


	public List<UserPayment> getUserPaymentList() {
		return userPaymentList;
	}


	public void setUserPaymentList(List<UserPayment> userPaymentList) {
		this.userPaymentList = userPaymentList;
	}


	public List<UserShipping> getUserShippingList() {
		return userShippingList;
	}


	public void setUserShippingList(List<UserShipping> userShippingList) {
		this.userShippingList = userShippingList;
	}


	public List<Orders> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public User() {

	}
	public User(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		//this.shippingAddress = user.getShippingAddress();

	}

	public User(String firstName, String lastName, String email, String password, boolean active, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
		//this.shippingAddress = shippingAddress;
	}

	public String toString() {
		return firstName + lastName + email + password + active + role /*+ shippingAddress*/;
	}


	public List<OrderDemo> getOrderDemos() {
		return orderDemos;
	}


	public void setOrderDemos(List<OrderDemo> orderDemos) {
		this.orderDemos = orderDemos;
	}

}
