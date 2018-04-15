package emma.store.service;

import emma.store.entity.ShippingAddress;
import emma.store.entity.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
