package emma.store.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.BookToCartItemDao;
import emma.store.dao.CartItemDao;
import emma.store.entity.Book;
import emma.store.entity.BookToCartItem;
import emma.store.entity.CartItem;
import emma.store.entity.Orders;
import emma.store.entity.ShoppingCart;
import emma.store.entity.User;
import emma.store.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private BookToCartItemDao bookToCartItemDao;


	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemDao.findByShoppingCart(shoppingCart);
	}


	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getPrice()).multiply(new BigDecimal(cartItem.getQty()));

		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);

		cartItemDao.save(cartItem);

		return cartItem;
	}


	public CartItem addBookToCartItem(Book book, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

		for (CartItem cartItem : cartItemList) {
			if(book.getId() == cartItem.getBook().getId()) {
				cartItem.setQty(1);
				cartItem.setSubtotal(new BigDecimal(book.getPrice()).multiply(new BigDecimal(qty)));
				cartItemDao.save(cartItem);
				return cartItem;
			}
		}

		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setBook(book);

		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(book.getPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemDao.save(cartItem);

		BookToCartItem bookToCartItem = new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		bookToCartItemDao.save(bookToCartItem);

		return cartItem;
	}


	public CartItem findById(Long id) {
		return cartItemDao.findOne(id);
	}

	public void removeCartItem(CartItem cartItem) {
		bookToCartItemDao.deleteByCartItem(cartItem);
		cartItemDao.delete(cartItem);
	}

	public CartItem save(CartItem cartItem) {
		return cartItemDao.save(cartItem);
	}

	public List<CartItem> findByOrders(Orders order) {
		return cartItemDao.findByOrders(order);
	}
}
