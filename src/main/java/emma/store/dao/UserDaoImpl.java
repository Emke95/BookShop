package emma.store.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

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
		User u=new User();				

		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setActive(true);

		this.sessionFactory.getCurrentSession().persist(u);

	}

	public List<User> findAll() {
		String sql = "Select new " + User.class.getName()//
                + "(u.FirstName, u.LastName, u.email,u.password, u.active,  u.role, u.address) " + " from "
                + User.class.getName() + " u "//
                + " order by u.FirstName desc";
        
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
      
        return query.list();
	}


}