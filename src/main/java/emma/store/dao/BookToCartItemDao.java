package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import emma.store.entity.BookToCartItem;
import emma.store.entity.CartItem;

@Repository
public interface BookToCartItemDao extends JpaRepository<BookToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}

