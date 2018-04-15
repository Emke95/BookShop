package emma.store.service;

import emma.store.entity.ShoppingCart;

public interface ShoppingCartService {
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	void clearShoppingCart(ShoppingCart shoppingCart);

}
