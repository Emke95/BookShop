package emma.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.CartItem;
import emma.store.entity.Orders;
import emma.store.entity.ShoppingCart;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long>{

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	List<CartItem> findByOrders(Orders orders);
}
