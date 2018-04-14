package emma.store.dao;

import java.util.Calendar;

import emma.store.entity.Book;
import emma.store.entity.OrderDetails;
public interface OrderDetailsDao {

	void saveOrderDetails(Calendar orderDate, int quantity, Book book);

	void addOrderDetails(OrderDetails orderDetails);

	void editOrderDetails(int orderDetailsId, Calendar orderDate, int quantity);

	void deleteOrderDetails(OrderDetails orderDetails);

	OrderDetails findOrderDetailsById(int orderDetailsId);

	OrderDetails findOrderDetailsByBookId(int bookId);

}

