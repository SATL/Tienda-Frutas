package controllers.Impl;

import controllers.CSVRecordLoader;
import controllers.OrderController;
import dommain.Order;
import dommain.Product;
import dommain.Purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderControllerImplCSV extends CSVRecordLoader<Purchase> implements OrderController {

    String filePath;

    public OrderControllerImplCSV(String filePath) {
        this.filePath = filePath;
    }

    protected Purchase parseRow(String[] row) {
        Purchase purchase = new Purchase();
        purchase.setProductName(row[0]);
        purchase.setQuantity( Integer.parseInt(row[1].replaceAll("\\s","")) ) ;
        return purchase;
    }

    @Override
    public List<Purchase> loadOrder() {
       List<Purchase> list= new ArrayList<>();
        try {
            list= this.parseCSV(filePath);
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    @Override
    public Order makeOrder(Map<String, Product> products){
        Order order = new Order();
        List<Purchase> orders = this.loadOrder();
        for(Purchase item:orders){
            order.purchase(products.get(item.getProductName()), item.getQuantity());
        }

        return order;
    }

}
