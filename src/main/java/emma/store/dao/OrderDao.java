package emma.store.dao;

import java.util.List;
import java.util.Map;

import emma.store.entity.*;

public interface OrderDao {
	
void addOrder(Order order);
	
	void addOrder(Map<Book, Integer> cartContents, User purchasedBy);
	
	void deleteOrder(int orderId);

	Order findOrderById(int orderId);
	
	List<Order> findAllOrders();
}
