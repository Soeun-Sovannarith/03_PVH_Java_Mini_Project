package org.example.Services;

import org.example.Models.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
  void writeProduct();
  void saveProduct(List<Product> products, String option) throws SQLException;
}
