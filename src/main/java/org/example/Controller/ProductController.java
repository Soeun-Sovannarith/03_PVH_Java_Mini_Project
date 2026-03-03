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
    public List<Product> writeProduct(){
           return productService.writeProduct();
    }
    public static void readProduct() {
        List<>
        String read_product = "SELECT * FROM stock";
        //query data
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(read_product);
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getInt("price");
                int qty = rs.getInt("qty");

                System.out.println("ID: " + id + " | Name: " + name + " | Unit Price" + price + " | QTY" + qty);
            }
            DisplayDataTable.displaytTable();
            System.out.println("Query User successfully!");
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public static void update() {
        String updateName = " update ? set name = ? where name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
        ) {
            conn.setAutoCommit(false);
            try (PreparedStatement pStm = conn.prepareStatement(updateName);
                 PreparedStatement pStm2 = conn.prepareStatement(updateName)
            ) {
                pStm.setString(1, "Kon khmer");
                pStm.setString(2, "Hello");
                pStm2.setString(1, "JDBC");
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
    }
}
