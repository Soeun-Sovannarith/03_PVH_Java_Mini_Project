package org.example.Controller;

import org.example.Models.Product;
import org.example.Services.ProductService;
import org.example.Services.ProductServiceImpl;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.DisplayDataTable;

import java.sql.*;
import java.util.List;

public class ProductController {
    public ProductService productService=new ProductServiceImpl();
    public List<Product> writeProduct() {
        return productService.writeProduct();
    }
    public void saveProduct(List<Product> products,String option) throws SQLException {
         productService.saveProduct(products,option);
    }
    public void unSaveProduct(List<Product> products,String option){
         productService.unSave(products,option);
    }
    public List<Product> readProduct() throws SQLException {
        return productService.readProduct();
    }

    public List<Product> readProduct(int limit) throws SQLException {
        return productService.readProduct(limit);
    }

    public void deleteProduct(int id) throws SQLException {
        productService.deleteProduct(id);
    }
    public void searchProduct(String name) throws SQLException {
        productService.searchProduct(name);
    }
    public void searchByIdProduct (int id) throws SQLException {
        productService.searchByIdProduct(id);
    }
}
