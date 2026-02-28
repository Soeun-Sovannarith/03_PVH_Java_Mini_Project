package org.example;
import org.example.Utilities.DatabaseUtil;
import java.sql.*;
class ReadProduct {
    public void readProduct() {
        String read_product = "SELECT * FROM test";
        //query data
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(read_product);
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getInt("price");
                int qty=rs.getInt("qty");

                System.out.println("ID: " + id + " | Name: " + name +" | Unit Price" +price +" | QTY"+qty);
            }
            System.out.println("Query User successfully!");
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }
}
