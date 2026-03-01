package org.example;

import org.example.Utilities.DatabaseUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        DatabaseUtil.getConnection();
        ReadProduct readProduct=new ReadProduct();
        readProduct.readProduct();
        Update update= new Update();
        update.update();
    }
}