package emma.store.service;

import java.util.List;
import java.util.Map;

import emma.store.entity.Book;
import emma.store.entity.OrderDemo;
import emma.store.entity.User;

public interface OrderDemoService {
	
	void addOrderDemo(OrderDemo orderDemo);
	
	void addOrderDemo(Map<Book, Integer> cartContents, User purchasedBy);

	void deleteOrderDemo(Long id);

	OrderDemo findOrderDemoById(Long id);
	
	List<OrderDemo> findAllOrderDemos();
}
