package emma.store.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMethodFactory {


	@SuppressWarnings("serial")
	public final static Map<String, String> mapOfPaymentMethods = new HashMap<String, String>() 
	{
		{
			put("CreditCard","Credit Card");
			put("DebitCard","Debit Card");
		}

	};
	public final static List<String> listOfPaymentMethods = new ArrayList<>(mapOfPaymentMethods.values());


	public static PaymentMethod getPaymentMethod(String type) {
		switch (type) {
		case "credit":
			return createCreditCard();
		case "debit":
			return createDebitCard();
		}
		throw new IllegalArgumentException();
	}
	public static CreditCard createCreditCard() {
		return new CreditCard("John Doe", "4111111111111111", "123", "01/22");
	}
	public static DebitCard createDebitCard() {
		return new DebitCard("John Doe", "4111111111111111", "123", "01/22");
	}
}