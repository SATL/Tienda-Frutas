package dommain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FreeOtherOffer extends Offer {

    String targetDisccount;

    public FreeOtherOffer(String product, String targetProduct,  String description, int quantity){
        this.originDisccount = product;
        this.targetDisccount = targetProduct;
        this.description = description;
        this.quantityToDisccount = quantity;
    }

    @Override
    public double getDisccount(Order order) {
        Map<String, Purchase> purchases = order.getPurchases();
        String product = getOriginDisccount();
        String productTarget = targetDisccount;

        if(isValid(order)){
            if(purchases.containsKey(productTarget)){
                return purchases.get(productTarget).getProduct().getPrice();
            }
        }
        return 0.0;
    }
}
