package emma.store.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import emma.store.entity.User;
import emma.store.entity.UserBilling;
import emma.store.entity.UserPayment;
import emma.store.entity.UserShipping;


public interface UserService  extends UserDetailsService {
	void save(User user);
	User findByEmail(String email);
	List<User> findAll();
	void delete(Long id);
	User findOne(Long id);
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	void updateUserShipping(UserShipping userShipping, User user);
	void setUserDefaultPayment(Long userPaymentId, User user);
	void setUserDefaultShipping(Long userShippingId, User user);
}