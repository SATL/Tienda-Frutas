package dommain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class DiscountTotalOffer extends Offer {

    double reducePrice = 0.0;
    double totalToApply;

    public DiscountTotalOffer(String product, String description, int total, int reducePrice){
        this.originDisccount = product;
        this.description = description;
        this.totalToApply = total;
        this.reducePrice = reducePrice;
    }

    @Override
    public double getDisccount(Order order) {
        Map<String, Purchase> purchases = order.getPurchases();
        String product = getOriginDisccount();
        if (isValid(order)) {
            double totalInProduct = purchases.get(product).getQuantity()* purchases.get(product).getProduct().getPrice();
            if(totalInProduct<totalToApply)
                return 0.0;
            int total = (int) Math.ceil(totalInProduct/totalToApply);
            return total*reducePrice;
        }

        return 0.0;
    }

    @Override
    public boolean isValid(Order order) {
        Map<String, Purchase> purchases = order.getPurchases();
        String product = getOriginDisccount();
        return purchases.containsKey(product);
    }
}
