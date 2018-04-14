package emma.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import emma.store.entity.CreditCard;
import emma.store.entity.ShippingAddress;
import emma.store.entity.User;
@Transactional
public interface UserDao {

	public User findById(int userId);
	public  User findByEmail(String email);
	public void edit(User user);
	public void register(User user);
	public ShippingAddress getAddress(User user, int shippingAddressId);
	public void save(ShippingAddress shippingAddress);
	public ArrayList<ShippingAddress> getMyAddresses(int userId);
	//public void save(ShippingAddress shippingAddress, User user);
	public void addCreditCard(CreditCard creditCard);
	public List<User> findAll();
}
