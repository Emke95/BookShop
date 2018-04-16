package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import emma.store.entity.Book;
import emma.store.entity.OrderDetails;


public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer>{

	OrderDetails findOrderDetailsByBook(Book book);

	OrderDetails findOrderDetailsByOrders(Long id);

	//OrderDetails findOrderDetailsById(Long id);

	//OrderDetails findOne(Long id);
}
