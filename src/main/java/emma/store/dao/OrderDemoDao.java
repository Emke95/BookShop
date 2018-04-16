package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.OrderDemo;

@Repository
public interface OrderDemoDao extends JpaRepository<OrderDemo, Long> {

}
