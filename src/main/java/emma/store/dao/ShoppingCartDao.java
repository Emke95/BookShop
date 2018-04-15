package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.ShoppingCart;


@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long> {

}
