package org.example;


import org.example.Controller.ProductController;
import org.example.Utilities.DatabaseUtil;

public class StockManagement {
    public static void run(){
        DatabaseUtil.getConnection();
        ProductController.readProduct();
        ProductController.update();
    }
}
