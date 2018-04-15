package emma.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.UserPaymentDao;

import emma.store.entity.UserPayment;
import emma.store.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	private UserPaymentDao userPaymentDao;
		
	public UserPayment findById(Long id) {
		return userPaymentDao.findOne(id);
	}
	
	public void removeById(Long id) {
		userPaymentDao.delete(id);
	}
}
