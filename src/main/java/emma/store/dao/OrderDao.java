package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders, Long> {

}
