package org.example.Services;

import org.example.Models.Product;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.DisplayDataTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product writeProduct() {
        return null;
    }
    @Override
    public List<Product> readProduct() {
        List<Product> listProduct = new ArrayList<>();
        String read_product = "SELECT * FROM stock";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(read_product);
        ) {
            int id;
            String name;
            double price;
            int qty;
            String import_date;
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getInt("price");
                qty = rs.getInt("qty");
                import_date = rs.getString("import_date");
                listProduct.add(new Product(id, name, price, qty, import_date));
            }
            DisplayDataTable.displaytTable(listProduct);
            System.out.println("Query User successfully!");
        } catch (SQLException e) {
            System.out.println("Fail to connect" + e);
        }
        return List.of();
    }
    @Override
    public Product updateProduct() {
        String updateName = " update stock set name = ? where name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
        ) {
            conn.setAutoCommit(false);
            try (PreparedStatement pStm = conn.prepareStatement(updateName);
                 PreparedStatement pStm2 = conn.prepareStatement(updateName)
            ) {
                pStm.setString(1, "Vea");
                pStm.setString(2, "Sathim");
                pStm2.setString(1, "Hello");
                pStm2.setString(2, "Veasna");
                pStm.executeUpdate();
                pStm2.executeUpdate();
                conn.commit();
                System.out.println("Update success!");
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}