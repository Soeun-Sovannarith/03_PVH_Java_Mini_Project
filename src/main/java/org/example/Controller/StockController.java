package org.example.Controller;

import org.example.Models.Product;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.DisplayDataTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockController {

    public static void deleteProduct() {
        String delete_product = "DELETE FROM stock WHERE id = ?";

    }
    public static void readProduct() {
        List<Product> listProduct=new ArrayList<>();
        String read_product = "SELECT * FROM stock";
        //query data
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(read_product);
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                String importDate = rs.getString("import_date");
                listProduct.add(new Product(id,name,price,qty,importDate));
//                System.out.println("ID: " + id + " | Name: " + name + " | Unit Price" + price + " | QTY" + qty + " | ImportDate " + importDate);
//                DisplayDataTable.displaytTable(listProduct);
            }
            DisplayDataTable.displaytTable(listProduct);
            System.out.println("Query User successfully!");
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public static void update() {
        String updateName = " update test set name = ? where name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
        ) {
            conn.setAutoCommit(false);
            try (PreparedStatement pStm = conn.prepareStatement(updateName);
                 PreparedStatement pStm2 = conn.prepareStatement(updateName)
            ) {
                pStm.setString(1, "Tonny");
                pStm.setString(2, "Endy");
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
