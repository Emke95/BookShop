package emma.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.OrderDemo;
import emma.store.entity.User;

@Repository
public interface OrderDemoDao extends JpaRepository<OrderDemo, Long> {

	List<OrderDemo> findByUser(User user);

	List<OrderDemo> findByUserId(Long id);

}
