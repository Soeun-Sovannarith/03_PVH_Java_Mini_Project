package org.example;

import org.example.Utilities.DatabaseUtil;


public class Main {
    public static void main(String[] args) {
//      DatabaseUtil.getConnection();
        ReadProduct readProduct=new ReadProduct();
        readProduct.readProduct();
        Update update= new Update();
        update.update();
    }
}