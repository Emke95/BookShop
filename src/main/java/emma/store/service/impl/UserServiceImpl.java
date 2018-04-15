package emma.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import emma.store.dao.UserDao;
import emma.store.dao.UserPaymentDao;
import emma.store.dao.UserShippingDao;
import emma.store.entity.ShoppingCart;
import emma.store.entity.User;
import emma.store.entity.UserBilling;
import emma.store.entity.UserPayment;
import emma.store.entity.UserShipping;
import emma.store.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserPaymentDao userPaymentDao;

	@Autowired
	private UserShippingDao userShippingDao;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s doesn't exist in database!", email));

		}
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				authorities
				);
	}

	@Override
	public void save(User user) {
		user.setActive(true);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		user.setShoppingCart(shoppingCart);
		userDao.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}


	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
	}

	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentDao.findAll();

		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentDao.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentDao.save(userPayment);
			}
		}
	}

	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingDao.findAll();

		for (UserShipping userShipping : userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingDao.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingDao.save(userShipping);
			}
		}
	}
}