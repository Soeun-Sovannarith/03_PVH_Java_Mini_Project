package org.example.Services;

import org.example.Models.Product;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.DisplayDataTable;
import org.example.Utilities.inputUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ProductServiceImpl implements ProductService {
    List<Product> productWrite=new ArrayList<>();
    List<Product> productsUpdate = new ArrayList<>();
    public static int idDatabase=0;
    @Override
    public List<Product> writeProduct() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd ");
        Date now = new Date();
        String date= sdfDate.format(now);
        inputUtil input =new inputUtil();
        String name=input.Inputname("Input Product Name:");
        double price=input.inputPrice("Input Unit Price :");
        int qty=input.qty("Input QTY:");
        productWrite.add(new Product(name,price,qty,date));
        return productWrite;
    }

    @Override
    public void saveProduct(List<Product> products, String option) {

        try (Connection con = DatabaseUtil.getConnection()) {
            con.setAutoCommit(false);
            if (option.equalsIgnoreCase("si")) {
                String insertSQL = "INSERT INTO stock(name, price, qty, import_date) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pt = con.prepareStatement(insertSQL)) {
                    for (Product product : products) {
                        pt.setString(1, product.getName());
                        pt.setDouble(2, product.getPrice());
                        pt.setInt(3, product.getQty());
                        pt.setString(4, product.getImport_date());

                        pt.executeUpdate();
                    }
                    con.commit();
                    System.out.println("Insert Success ");
                    products.clear();
                }

            } else if (option.equalsIgnoreCase("su")) {

                String updateSQL = "UPDATE stock SET name=?, price=?, qty=?, import_date=? WHERE id=?";

                try (PreparedStatement pt = con.prepareStatement(updateSQL)) {

                    for (Product product : products) {

                        pt.setString(1, product.getName());
                        pt.setDouble(2, product.getPrice());
                        pt.setInt(3, product.getQty());
                        pt.setString(4, product.getImport_date());
                        pt.setInt(5, product.getId()); // FIXED

                        pt.executeUpdate();
                    }

                    con.commit();
                    System.out.println("Update Success ");
                    products.clear();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> readProduct() throws SQLException {
        return readProduct(10); // Default to 10 rows
    }

    @Override
    public List<Product> readProduct(int limit) throws SQLException {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT id, name, price, qty, import_date, row_state FROM stock LIMIT ?";

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {

            pt.setInt(1, limit);

            try (ResultSet rs = pt.executeQuery()) {
                while (rs.next()) {

                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int qty = rs.getInt("qty");
                    String import_date = rs.getString("import_date");
                    String rowState = rs.getString("row_state");

                    products.add(new Product(id, name, price, qty, import_date, rowState));
                }
            }
        }
        return products;
    }

    @Override
    public void deleteProduct(int id) throws SQLException {

    }

    @Override
    public void searchProduct(String name) throws SQLException {

    }

    @Override
    public void recoveryrow(int id) throws SQLException {

    }


}
