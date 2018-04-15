package emma.store.service;

import emma.store.entity.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	void removeById(Long id);
}
