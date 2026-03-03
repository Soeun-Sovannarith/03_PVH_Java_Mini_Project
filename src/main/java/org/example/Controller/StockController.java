package org.example.Controller;

import org.example.Models.Product;
import org.example.Services.ProductServiceImpl;

import java.util.List;

public class StockController {
    public static List<Product> readProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();
        return productService.readProduct();
    }
    static ProductServiceImpl productService = new ProductServiceImpl();

    public static void getProductService() {
        productService.updateProduct();
    }
}
