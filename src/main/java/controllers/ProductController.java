package controllers;

import dommain.Product;

import java.util.HashMap;
import java.util.Map;

public interface ProductController {
    Map<String, Product> loadProducts();
}
