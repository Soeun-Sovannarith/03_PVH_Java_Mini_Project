package org.example;


import org.example.Controller.StockController;
import org.example.Utilities.DatabaseUtil;

public class StockManagement {
    public static void run(){
        DatabaseUtil.getConnection();
        StockController.readProduct();
        StockController.update();
    }
}
