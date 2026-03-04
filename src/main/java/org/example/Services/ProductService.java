package org.example.Services;

import org.example.Models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public interface ProductService {
  List<Product> writeProduct();
  void saveProduct(List<Product> products, String option) throws SQLException;
  void unSave(List<Product> products,String option);
  List<Product> update(int id) throws SQLException;
  List<Product> readProduct() throws SQLException;
  List<Product> readProduct(int limit) throws SQLException;
  void deleteProduct(int id) throws SQLException;
  void searchByIdProduct(int id) throws SQLException;
  void searchProduct(String name) throws SQLException;
  void recoveryrow(int id) throws SQLException;
}
