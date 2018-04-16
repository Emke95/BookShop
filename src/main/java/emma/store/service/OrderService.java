package emma.store.service;

import java.util.List;

import emma.store.entity.*;

public interface OrderService {

	Orders createOrders(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			Payment payment,
			User user);

	Orders findOne(Long id);
	List<Orders> findByUser(User user);
	List<Orders> findAll();
}
