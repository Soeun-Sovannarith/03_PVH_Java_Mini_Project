package org.example.Services;

import org.example.Models.Product;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.inputUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
        List<Product> product=new ArrayList<>();
        product.add(new Product(name,price,qty,date));
        return product;
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

        List<Product> products = new ArrayList<>();

        try (Connection con = DatabaseUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM stock")) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                String import_date = rs.getString("import_date");

                products.add(new Product(id, name, price, qty, import_date));
            }
        }

        return products;
    }
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter new qty: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE stock SET name=?, price=?, qty=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Update success!");
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            System.out.println("Exception "+e);
        }
    }

}
