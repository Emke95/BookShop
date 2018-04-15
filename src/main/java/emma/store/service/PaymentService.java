package emma.store.service;

import emma.store.entity.Payment;
import emma.store.entity.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
