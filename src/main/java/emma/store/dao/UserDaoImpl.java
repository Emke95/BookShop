package emma.store.dao;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import emma.store.entity.CreditCard;
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

	public User findById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("userId", userId));
		return (User) crit.uniqueResult();
	}
	public void register(User user) {	
		Session session = sessionFactory.openSession();
		user.setActive(true);
		session.save(user);
		session.close();
	}

	/*public void save(ShippingAddress shippingAddress, User user) {

		User u = findById(user.getUserId());
		Session session = sessionFactory.openSession();
		shippingAddress.setUser(u);
		session.save(shippingAddress);
		session.close();
	}*/

	public ShippingAddress getAddress(User user, int shippingAddressId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ShippingAddress where shippingAddressId = :shippingAddressId and userId = :userId");
		q.setInteger("shippingAddressId", shippingAddressId);
		q.setInteger("userId", user.getUserId());
		ShippingAddress shippingAddress = (ShippingAddress) q.uniqueResult();

		return shippingAddress;

	}

	public void save(ShippingAddress shippingAddress) {
		Session session = sessionFactory.openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			session.save(shippingAddress);
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("Hello " + e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		/*int shippingAddressId = shippingAddress.getShippingAddressId();
		ShippingAddress address = null;
		address= new ShippingAddress();
		address.setShippingAddressId(shippingAddressId);
		address.setHouseNo(shippingAddress.getHouseNo());
		address.setStreet(shippingAddress.getStreet());
		address.setArea(shippingAddress.getArea());
		address.setCounty(shippingAddress.getCounty());
		address.setCountry(shippingAddress.getCountry());
		address.setUser(shippingAddress.getUser());
		this.sessionFactory.getCurrentSession().persist(address);
		 */
	}

	public void edit(User user) {
		User u = findById(user.getUserId());
		if(u!=null) {
			u.setEmail(user.getEmail());
			u.setFirstName(u.getFirstName());
			u.setLastName(u.getLastName());
			u.setPassword(u.getPassword());
			//u.setShippingAddress(u.getShippingAddress());
		}
	}

	public ArrayList<ShippingAddress> getMyAddresses(int userId)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ShippingAddress where userId = :userId");
		query.setInteger("userId", userId);
		ArrayList<ShippingAddress> addressList = (ArrayList<ShippingAddress>) query.list();
		
		return addressList;
	}
	
	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		List<User> users=session.createCriteria(User.class).list();
		System.out.println(users);
		session.close();
		return users;
	}

	public void addCreditCard(CreditCard creditCard) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(creditCard);
		session.flush(); 
	}
}