package emma.store.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.ShoppingCartDao;
import emma.store.entity.CartItem;
import emma.store.entity.ShoppingCart;
import emma.store.service.CartItemService;
import emma.store.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {

		BigDecimal cartTotal = new BigDecimal(0);
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getQuantity() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		shoppingCart.setGrandTotal(cartTotal);
		shoppingCartDao.save(shoppingCart);

		return shoppingCart;
	}

	@Override
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}

		shoppingCart.setGrandTotal(new BigDecimal(0));

		shoppingCartDao.save(shoppingCart);
	}

}
