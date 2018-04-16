package emma.store.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emma.store.dao.OrderDetailsDao;
import emma.store.entity.Book;
import emma.store.entity.OrderDetails;
import emma.store.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsDao orderDetailsDao;

	public void addOrderDetails(Date orderDate, int quantity, Book book) {
		OrderDetails od = new OrderDetails(orderDate, quantity, book);
		orderDetailsDao.save(od);
	}

	@Transactional
	public void addOrderDetails(OrderDetails orderDetails) {
		orderDetailsDao.save(orderDetails);

	}


//	@Transactional
//	public void editOrderDetails(Long id, Date orderDate, int quantity) {
//		OrderDetails od = findOrderDetailsById(id);
//		if(orderDate != null){
//			od.setOrderDate(orderDate);
//		}
//		if(quantity != 0){
//			od.setQuantity(quantity);
//		}
//		orderDetailsDao.save(od);
//	}

//	@Transactional
//	public void deleteOrderDetails(Long id) {
//		OrderDetails od = findOrderDetailsById(id);
//		orderDetailsDao.delete(od);
//	}

//	@Override
//	public OrderDetails findOrderDetailsById(Long id) {
//		return orderDetailsDao.findOne(id);
//	}

	@Transactional
	public void deleteAllOrderDetails() {
		orderDetailsDao.deleteAll();
	}

}
