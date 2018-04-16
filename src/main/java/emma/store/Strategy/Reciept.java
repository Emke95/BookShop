package emma.store.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Reciept {
    private List<LineItem> lineItems = new ArrayList<>();
    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }
    public int getCostInCents() {
        return lineItems.stream().mapToInt(item -> item.getCostInEuro()).sum();
    }
    public void pay(PaymentMethod method) {
        method.pay(getCostInCents());
    }
}