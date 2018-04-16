package emma.store.Strategy;

public class CreditCard extends Card {
	public CreditCard(String nameOnCard, String number, String cvc, String expirationDate) {
		super(nameOnCard, number, cvc, expirationDate);
	}
	@Override
	protected String getType() {
		return "credit";
	}
	@Override
	protected void executeTransaction(int euro) {
		// Contact credit holder to make transaction
	}
}