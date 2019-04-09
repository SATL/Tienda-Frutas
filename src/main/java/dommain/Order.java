package dommain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Order {
    private Map<String, Purchase> purchases = new HashMap<>();
    private List<Offer> offers = new ArrayList<>();
    Double total = 0.0;

    public void purchase(Product product, Integer quantity) {
        Purchase purchase = new Purchase();
        purchase.setQuantity(quantity);
        purchase.setProduct(product);
        purchases.put(product.getName(), purchase);
    }


    public String receipt() {
        String receipt = "Products: \n";

        for (Map.Entry<String, Purchase> entry : purchases.entrySet()) {
            Purchase purchase = entry.getValue();
            String product = purchase.getProduct().getName();
            Double price = purchase.getProduct().getPrice();
            Double toAdd = purchase.quantity * price;
            total += toAdd;
            receipt += product + " " + purchase.quantity + ": " + price + " " + toAdd + "\n";
        }

        receipt += "_______________________\n\nDiscounts: \n";

        double disccount = 0.0;
        for (Offer offer : offers) {
            double toApply = offer.getDisccount(this);
            if (toApply <= 0)
                break;
            receipt += offer.description + ": " + toApply + "\n";
            disccount += toApply;
        }

        receipt += "\nTotal disccount: " + disccount + "\n_____________________\n";
        receipt += "\nTotal: " + (total - disccount);
        return receipt;
    }

}
