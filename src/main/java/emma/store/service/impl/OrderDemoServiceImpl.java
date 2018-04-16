package emma.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emma.store.dao.OrderDemoDao;
import emma.store.dao.OrderDetailsDao;
import emma.store.entity.Book;
import emma.store.entity.OrderDemo;
import emma.store.entity.OrderDetails;
import emma.store.entity.User;
import emma.store.service.OrderDemoService;

@Service
public class OrderDemoServiceImpl implements OrderDemoService {

	@Autowired
	private OrderDemoDao orderDemoDao;

	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	public void addOrderDemo(OrderDemo orderDemo) {
		orderDemoDao.save(orderDemo);
	}

	@Transactional
	public void addOrderDemo(Map<Book, Integer> cartContents, User purchasedBy) {
		Date today = new Date();
		OrderDemo order = new OrderDemo();
		order.setOrderDate(today);
		order.setUser(purchasedBy);
		List<OrderDetails> details = new ArrayList<>();
		for (Entry<Book, Integer> entry : cartContents.entrySet()) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setBook(entry.getKey());
			orderDetails.setQuantity(entry.getValue());
			orderDetails.setOrderDate(today);
			orderDetails.setOrderDemos(order);
			details.add(orderDetails);
			orderDetailsDao.save(orderDetails);
		}
		order.setOrderDetails(details);
		this.addOrderDemo(order);
	}

	@Transactional
	public void deleteOrderDemo(Long id) {
		OrderDemo order = findOrderDemoById(id);
		orderDemoDao.delete(order);
	}

	@Transactional
	public OrderDemo findOrderDemoById(Long id) {
		return orderDemoDao.findOne(id);
	}

	
	public List<OrderDemo> findAllOrderDemos() {
		return orderDemoDao.findAll();
	}

}
