package org.example.Models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int qty;
    private String import_date;
    private static int idIndex=0;
    public Product(String name, double price, int qty, String import_date){
        ++idIndex;
        this.id=idIndex;
        this.name=name;
        this.price=price;
        this.qty=qty;
        this.import_date=import_date;
    }
    public Product(int id, String name, double price, int qty, String import_date){

        this.id=id;
        this.name=name;
        this.price=price;
        this.qty=qty;
        this.import_date=import_date;
    }

    public String getImport_date() {
        return import_date;
    }

    public Product setImport_date(String import_date) {
        this.import_date = import_date;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
