package emma.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.Orders;
import emma.store.entity.User;

@Repository
public interface OrderDao extends JpaRepository<Orders, Long> {


	List<Orders> findByUser(User user);
}
