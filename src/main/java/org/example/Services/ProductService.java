package org.example.Services;

import org.example.Models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public interface ProductService {
  List<Product> writeProduct();
  void saveProduct(List<Product> products, String option) throws SQLException;
  List<Product> readProduct() throws SQLException;

    void updateProduct();

}
