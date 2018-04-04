package emma.store.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import emma.store.entity.ShippingAddress;
import emma.store.entity.User;
@Transactional
public interface UserDao {

	public  User findByEmail(String email);

	public void register(User user);

	public void save(ShippingAddress shippingAddress);
		
	public List<User> findAll();
}
