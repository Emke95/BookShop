package emma.store.entity;

import javax.persistence.*;

@Entity
@Table(name="BookToCartItem")
public class BookToCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="BookToCartId")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="bookId")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="cartItemId")
	private CartItem cartItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

}

