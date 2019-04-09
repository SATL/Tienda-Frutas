package controllers;

import dommain.Order;
import dommain.Product;
import dommain.Purchase;

import java.util.List;
import java.util.Map;

public interface OrderController {
    List<Purchase> loadOrder();

    Order makeOrder(Map<String, Product> products);
}
