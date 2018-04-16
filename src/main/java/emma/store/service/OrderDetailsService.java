package emma.store.service;

import java.util.Date;

import emma.store.entity.Book;
import emma.store.entity.OrderDetails;

public interface OrderDetailsService {

void addOrderDetails(Date orderDate, int quantity, Book book);
	
	void addOrderDetails(OrderDetails orderDetails);

	void deleteAllOrderDetails();
}
