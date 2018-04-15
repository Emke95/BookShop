package emma.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.UserShippingDao;
import emma.store.entity.UserShipping;
import emma.store.service.UserShippingService;

@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingDao userShippingDao;

	@Override
	public UserShipping findById(Long id) {
		return userShippingDao.findOne(id);
	}

	@Override
	public void removeById(Long id) {
		userShippingDao.delete(id);
	}
	
}
