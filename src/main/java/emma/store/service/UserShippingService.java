package emma.store.service;

import emma.store.entity.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	void removeById(Long id);
}
