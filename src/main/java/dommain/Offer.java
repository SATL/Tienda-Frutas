package dommain;

import lombok.Data;

import java.util.Map;

@Data
public abstract class Offer {
    protected String description;
    protected int quantityToDisccount;
    protected String originDisccount;

    public abstract double getDisccount(Order order);

    protected boolean isValid(Order order) {
        Map<String, Purchase> purchases = order.getPurchases();
        String product = getOriginDisccount();

        return purchases.containsKey(product) && purchases.get(product).getQuantity() >= getQuantityToDisccount();
    }
}
