package controllers.Impl;

import controllers.CSVRecordLoader;
import controllers.ProductController;
import dommain.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductControllerImplCSV extends CSVRecordLoader<Product> implements ProductController {

    String filePath;

    public ProductControllerImplCSV(String filePath) {
        this.filePath = filePath;
    }

    protected Product parseRow(String[] row) {
        return new Product(row[0], Double.parseDouble(row[1].replaceAll("\\s","")));
    }

    @Override
    public Map<String, Product> loadProducts() {
        Map<String, Product> map= new HashMap<>();
        try {
            List<Product> list= this.parseCSV(filePath);
            for(Product product:list){
                map.put(product.getName(), product);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return map;
    }
}
