package emma.store.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.OrderDao;
import emma.store.entity.Book;
import emma.store.entity.CartItem;
import emma.store.entity.Orders;
import emma.store.entity.Payment;
import emma.store.entity.ShippingAddress;
import emma.store.entity.ShoppingCart;
import emma.store.entity.User;
import emma.store.service.CartItemService;
import emma.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CartItemService cartItemService;

	@Override
	public Orders createOrders(ShoppingCart shoppingCart, ShippingAddress shippingAddress, Payment payment, User user) {
		Orders orders = new Orders();
		orders.setPayment(payment);
		orders.setShippingAddress(shippingAddress);
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for(CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrders(orders);
			book.setQuantity(book.getQuantity() - cartItem.getQty());
		}

		orders.setCartItemList(cartItemList);
		orders.setOrderDate(Calendar.getInstance().getTime());
		orders.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrders(orders);
		payment.setOrders(orders);
		orders.setUser(user);
		orders = orderDao.save(orders);

		return orders;
	}
	
	public List<Orders> findByUser(User user) {
		return orderDao.findByUser(user);
	}


	public Orders findOne(Long id) {
		return orderDao.findOne(id);
	}

	
	public List<Orders> findAll() {
		return orderDao.findAll();
	}
}
