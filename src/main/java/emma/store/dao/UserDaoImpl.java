package emma.store.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import emma.store.entity.ShippingAddress;
import emma.store.entity.User;
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		return (User) crit.uniqueResult();
	}

	public void register(User user) {	
		Session session = sessionFactory.openSession();
		user.setActive(true);
		session.save(user);
		session.close();
	}

	public void save(ShippingAddress shippingAddress) {
		Session session = sessionFactory.openSession();
		session.save(shippingAddress);
		session.close();
	}

	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		List<User> users=session.createCriteria(User.class).list();
		System.out.println(users);
		session.close();
		return users;
	}
}