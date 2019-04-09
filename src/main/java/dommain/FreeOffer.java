package dommain;

import java.util.Map;

public class FreeOffer extends Offer {

    public FreeOffer(String product, String description, int quantity){
        this.originDisccount = product;
        this.description = description;
        this.quantityToDisccount = quantity;
    }

    @Override
    public double getDisccount(Order order) {
        Map<String, Purchase> purchases = order.getPurchases();
        String product = originDisccount;
        if (isValid(order)) {
            return purchases.get(product).getProduct().getPrice();
        }
        return 0.0;
    }
}
