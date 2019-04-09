import controllers.Impl.OrderControllerImplCSV;
import controllers.OrderController;
import controllers.ProductController;
import controllers.Impl.ProductControllerImplCSV;
import dommain.*;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        ProductController productController = new ProductControllerImplCSV("./products.csv");
        Map<String, Product> map = productController.loadProducts();

        OrderController orderController = new OrderControllerImplCSV("./orders.csv");

        Order order = orderController.makeOrder(map);

        /***
         * No description about how to add disccount, can be added programmed, or extends CSV controller similar than orders
         */

        //Offer not comulative
        Offer manzana = new FreeOffer("Manzana", "Compre 3 y pague 2 en Manzanas", 3);

        //Offer not comulative
        FreeOtherOffer pera1 = new FreeOtherOffer("Pera", "Naranja", "Llévese 2 Peras y la primera Naranja le sale gratis.", 2);
        DiscountTotalOffer discount = new DiscountTotalOffer("Pera", "Por cada 4 € gastados en Peras, le descontamos un euro su factura final.", 4, 1);

        order.getOffers().add(manzana);
        order.getOffers().add(pera1);
        order.getOffers().add(discount);

        System.out.println(order.receipt());

    }
}
