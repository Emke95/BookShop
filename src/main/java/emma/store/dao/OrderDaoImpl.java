package emma.store.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import emma.store.entity.Book;
import emma.store.entity.Cart;
import emma.store.entity.Order;
import emma.store.entity.OrderDetails;
import emma.store.entity.User;

@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addOrder(Order order) {
		Session session = sessionFactory.openSession();
		session.save(order);
	}

	@Override
	public void addOrder(Map<Book, Integer> cartContents, User purchasedBy) {
		Calendar now = Calendar.getInstance();
		Order order = new Order();
		order.setOrderDate(now);
		order.setUser(purchasedBy);
		List<OrderDetails> details = new ArrayList<>();
		for (Entry<Book, Integer> entry : cartContents.entrySet()) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setBook(entry.getKey());
			orderDetails.setQuantity(entry.getValue());
			orderDetails.setOrderDate(now);
			orderDetails.setOrder(order);
			details.add(orderDetails);
			orderDetailsDao.addOrderDetails(orderDetails);
		}
		order.setOrderDetails(details);
		this.addOrder(order);
	}

	@Override
	public void deleteOrder(int orderId) {
		Order order = findOrderById(orderId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
	}

	@Override
	public Order findOrderById(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Order.class);
		crit.add(Restrictions.eq("orderId", orderId));
		return (Order) crit.uniqueResult();
	}

	@Override
	public List<Order> findAllOrders() {
		Session session = sessionFactory.openSession();
		List<Order> orders=session.createCriteria(Order.class).list();
		System.out.println(orders);
		session.close();
		return orders;
	}

}