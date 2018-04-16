package emma.store.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="CartItem")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CartItemId")
	private Long id;
	
	@Column(name = "Quantity")
	private int qty;
	
	@Column(name = "Subtotal")
	private BigDecimal subtotal;
	
	@OneToOne
	private Book book;
	
	@OneToMany(mappedBy = "cartItem")
	private List<BookToCartItem> bookToCartItemList;

	@ManyToOne
	@JoinColumn(name="shoppingCartId")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private Orders orders;
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookToCartItem> getBookToCartItemList() {
		return bookToCartItemList;
	}

	public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
		this.bookToCartItemList = bookToCartItemList;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
}
