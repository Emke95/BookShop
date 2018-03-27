package emma.store.dao;

import java.util.List;

import emma.store.entity.User;

public interface UserDao {
	
	public  User findByEmail(String email);

	public void register(User user);

	public List<User> findAll();

}
