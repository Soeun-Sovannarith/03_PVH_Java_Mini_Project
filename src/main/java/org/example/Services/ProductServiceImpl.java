package org.example.Services;

import org.example.Models.Product;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.inputUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ProductServiceImpl implements ProductService {
    List<Product> productWrite=new ArrayList<>();
    List<Product> productsUpdate = new ArrayList<>();
    public static int idDatabase=0;
    @Override
    public void writeProduct() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd ");
        Date now = new Date();
        String date= sdfDate.format(now);
        inputUtil input =new inputUtil();
        String name=input.Inputname("Input Product Name:");
        double price=input.inputPrice("Input Unit Price :");
        int qty=input.qty("Input QTY:");
        productWrite.add(new Product(name,price,qty,date));
    }

    @Override
    public void saveProduct(List<Product> products, String option) throws SQLException {
        Connection con=DatabaseUtil.getConnection();
        if(option.equals("si")){
            String insertSQL = "INSERT INTO stock(name, unit_price, qty, import_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pt = con.prepareStatement(insertSQL)) {
                for (Product product : products) {
                    pt.setString(1, product.getName());
                    pt.setDouble(2, product.getPrice());
                    pt.setInt(3, product.getQty());
                    pt.setString(4, product.getImport_date());
                    pt.executeUpdate();
                    System.out.println("Insert Success");
                }
                con.commit(); // Commit transaction
                products.clear(); // Remove all products after successful save
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException(e);
            }

        } else if (option.equals("su")) {
            String updateSQL = "UPDATE stock SET name=?, unit_price=?, qty=?, import_date=? WHERE id=?";
            try (PreparedStatement pt = con.prepareStatement(updateSQL)) {
                for (Product product : products) {
                    pt.setString(1, product.getName());
                    pt.setDouble(2, product.getPrice());
                    pt.setInt(3, product.getQty());
                    pt.setString(4, product.getImport_date());
                    pt.setInt(5, idDatabase);
                    pt.executeUpdate();
                    System.out.println("Update Success");
                }
                con.commit();
                products.clear();
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
