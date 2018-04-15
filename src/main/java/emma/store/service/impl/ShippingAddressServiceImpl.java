package emma.store.service.impl;

import org.springframework.stereotype.Service;

import emma.store.entity.ShippingAddress;
import emma.store.entity.UserShipping;
import emma.store.service.ShippingAddressService;


@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		shippingAddress.setHouseNo(userShipping.getuHouseNo());
		shippingAddress.setStreet(userShipping.getuStreet());
		shippingAddress.setArea(userShipping.getuArea());
		shippingAddress.setCounty(userShipping.getaCounty());
		shippingAddress.setCountry(userShipping.getaCountry());

		return shippingAddress;
	}
}
