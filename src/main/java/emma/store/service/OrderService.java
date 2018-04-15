package emma.store.service;

import emma.store.entity.*;

public interface OrderService {

	Orders createOrders(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			Payment payment,
			User user);
	
	Orders findOne(Long id);
}
