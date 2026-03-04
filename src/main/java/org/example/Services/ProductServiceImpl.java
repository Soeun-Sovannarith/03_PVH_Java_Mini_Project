package org.example.Services;

import org.example.Models.Product;
import org.example.Utilities.Color;
import org.example.Utilities.DatabaseUtil;
import org.example.Utilities.DisplayDataTable;
import org.example.Utilities.inputUtil;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    List<Product> productWrite = new ArrayList<>();
    List<Product> productsUpdate = new ArrayList<>();
    public static int idDatabase = 0;
    private int currentId = -1;

    @Override
    public List<Product> writeProduct() {

        try {

            int nextId = getNextId();
            if (currentId == -1) {
                currentId = getNextId();
            } else {
                currentId++;
            }
            System.out.println("New Product ID = " + +currentId);

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            String date = sdfDate.format(now);

            inputUtil input = new inputUtil();

            String name = input.Inputname("Input Product Name: ");
            double price = input.inputPrice("Input Unit Price: ");
            int qty = input.qty("Input QTY: ");

            productWrite.add(new Product(name, price, qty, date));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productWrite;
    }

    private int getNextId() throws SQLException {
        String sql = "SELECT COALESCE(MAX(id),0) + 1 AS next_id FROM stock";

        try (Connection con = DatabaseUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("next_id");
            }
        }
        return 1;
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
                int count = 0;
                try (PreparedStatement pt = con.prepareStatement(updateSQL)) {
                    for (Product product : products) {

                        pt.setString(1, product.getName());
                        pt.setDouble(2, product.getPrice());
                        pt.setInt(3, product.getQty());
                        pt.setString(4, product.getImport_date());
                        pt.setInt(5, product.getId()); // FIXED

                        pt.executeUpdate();
                        count++;
                        System.out.println("Product " + count + " Updated Successfully ");
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
    public void unSave(List<Product> products, String option) {
        DisplayDataTable.displaytTable(products);
    }

    @Override
    public List<Product> update(int id) throws SQLException {

        List<Product> selected = new ArrayList<>();
        inputUtil input = new inputUtil();

        String sql = "SELECT * FROM stock WHERE id=?";

        try (Connection con = DatabaseUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Product not found!");
                return selected;
            }
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            int qty = rs.getInt("qty");
            String importDate = rs.getString("import_date"); // keep original

            List<Product> display = new ArrayList<>();
            display.add(new Product(id, name, price, qty, importDate));
            DisplayDataTable.displaytTable(display);
            System.out.print("1.Name  ");
            System.out.print("2.Unit Price  ");
            System.out.print("3.Qty  ");
            System.out.print("4.All Fields  ");
            System.out.print("5.Exit  ");

            int choose = input.qty("\nChoose an option to update: ");

            switch (choose) {
                case 1 -> {
                    name = input.Inputname("Input Product Name: ");
                }
                case 2 -> {
                    price = input.inputPrice("Enter price: ");
                }
                case 3 -> {
                    qty = input.qty("Input Quantity: ");
                }
                case 4 -> {
                    name = input.Inputname("Input Product Name: ");
                    price = input.inputPrice("Enter price: ");
                    qty = input.qty("Input Quantity: ");
                }
                case 5 -> {
                    System.out.println("Update cancelled.");
                    return selected;
                }
                default -> {
                    System.out.println("Invalid option");
                    return selected;
                }
            }

            selected.add(new Product(id, name, price, qty, importDate));
        }

        return selected;
    }

    @Override
    public List<Product> readProduct() throws SQLException {
        return readProduct(1, 10);
    }

    @Override
    public int countAllProducts() throws SQLException {
        String sql = "SELECT COUNT(*) FROM stock";
        try (Connection con = DatabaseUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public List<Product> readProduct(int page, int limit) throws SQLException {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * limit;

        String sql = "SELECT id, name, price, qty, import_date FROM stock ORDER BY id ASC LIMIT ? OFFSET ?";

        try (Connection con = DatabaseUtil.getConnection();
                PreparedStatement pt = con.prepareStatement(sql)) {

            pt.setInt(1, limit);
            pt.setInt(2, offset);

            try (ResultSet rs = pt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int qty = rs.getInt("qty");
                    String import_date = rs.getString("import_date");
                    products.add(new Product(id, name, price, qty, import_date));
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> readProduct(int limit) throws SQLException {
        return readProduct(1, limit);
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String deleteSQL = "DELETE FROM stock WHERE id=?";
        try (Connection con = DatabaseUtil.getConnection();
                PreparedStatement pt = con.prepareStatement(deleteSQL)) {
            pt.setInt(1, id);
            int rowsAffected = pt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delete Success");
            } else {
                System.out.println("Delete Failed");
            }
        }
    }

    @Override
    public void searchByIdProduct(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection con = DatabaseUtil.getConnection();
        String searchByIdSQL = "SELECT * FROM stock WHERE id=?";
        try (PreparedStatement pt = con.prepareStatement(searchByIdSQL)) {
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                String import_date = rs.getString("import_date");
                products.add(new Product(idProduct, name, price, qty, import_date));
            }
        }
        DisplayDataTable.displaytTable(products);
    }

    @Override
    public void searchProduct(String name) throws SQLException {
        List<Product> productByName = new ArrayList<>();
        Connection con = DatabaseUtil.getConnection();
        String selectSQL = "SELECT * FROM stock WHERE name ILIKE ? ORDER BY id ASC";
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {
            ps.setString(1, name + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameproduct = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                String import_date = rs.getString("import_date");
                productByName.add(new Product(id, nameproduct, price, qty, import_date));
            }
        }
        DisplayDataTable.displaytTable(productByName);
    }

    @Override
    public void recoveryrow(int id) throws SQLException {

    }

    public void restoreProduct() {
        File[] files = new File("src/backup/").listFiles((d, n) -> n.endsWith(".sql"));
        if (files == null || files.length == 0) {
            System.out.println("No backup found.");
            return;
        }

        System.out.println("+-----+------------------------------------------+");
        System.out.printf("|  %-42s|%n", "     List of Backup Data");
        System.out.println("+-----+------------------------------------------+");
        for (int i = 0; i < files.length; i++)
            System.out.printf("| %-3d | %-40s |%n", i + 1, files[i].getName());
        System.out.println("+-----+------------------------------------------+");

        inputUtil input = new inputUtil();
        int choice = input.inputId("=> Enter backup_id to restore: ") - 1;
        if (choice < 0 || choice >= files.length) {
            System.out.println("Cancelled.");
            return;
        }

        try (Connection con = DatabaseUtil.getConnection();
                BufferedReader br = new BufferedReader(new FileReader(files[choice]))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    con.createStatement().execute(line.trim());
            System.out.println(Color.green + "Database restore successful!" + Color.reset);
        } catch (Exception e) {
            System.out.println("Restore failed: " + e.getMessage());
        }
    }

    public void backupProduct() {
        try (Connection con = DatabaseUtil.getConnection();
                ResultSet rs = con.createStatement()
                        .executeQuery("SELECT * FROM stock ORDER BY id")) {

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String file = "src/backup/Version1-product-backup-" + date + ".sql";
            new File("src/backup/").mkdirs();

            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println("DELETE FROM stock;");
            while (rs.next()) {
                pw.printf("INSERT INTO stock VALUES (%d,'%s',%.2f,%d,'%s');%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("qty"),
                        rs.getString("import_date"));
            }
            pw.close();
            System.out.println("Database backup successful!");

        } catch (Exception e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }

}
