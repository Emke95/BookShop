package emma.store.dao;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import emma.store.entity.Book;
import emma.store.entity.OrderDetails;

@Transactional
public class OrderDetailsDaoImpl implements OrderDetailsDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrderDetails(Calendar orderDetails, int quantity, Book book) {
		OrderDetails od = new OrderDetails(orderDetails, quantity, book);
		Session session = sessionFactory.openSession();
		session.save(od);
		session.close();
	}

	public void addOrderDetails(OrderDetails orderDetails) {
		Session session = sessionFactory.openSession();
		session.save(orderDetails);
		session.close();
	}

	public void editOrderDetails(int orderDetailsId, Calendar orderDate, int quantity) {
		OrderDetails od = findOrderDetailsById(orderDetailsId);
		if(orderDate != null){
			od.setOrderDate(orderDate);
		}
		if(quantity != 0){
			od.setQuantity(quantity);
		}
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(od);
		session.close();
	}

	public void deleteOrderDetails(OrderDetails orderDetails) {
		this.sessionFactory.getCurrentSession().delete(orderDetails);
	}

	public OrderDetails findOrderDetailsById(int orderDetailsId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(OrderDetails.class);
		crit.add(Restrictions.eq("orderDetailsId", orderDetailsId));
		return (OrderDetails) crit.uniqueResult();

	}


	public OrderDetails findOrderDetailsByBookId(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(OrderDetails.class);
		crit.add(Restrictions.eq("bookId", bookId));
		return (OrderDetails) crit.uniqueResult();


	}

}