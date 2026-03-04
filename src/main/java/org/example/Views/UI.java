package org.example.Views;

import org.example.Controller.ProductController;
import org.example.Models.Product;
import org.example.Services.SettingService;
import org.example.Services.SettingsServiceImpl;
import org.example.Utilities.Color;
import org.example.Utilities.DisplayDataTable;
import org.example.Utilities.Menu;
import org.example.Utilities.inputUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UI {
    ProductController productController = new ProductController();
    SettingService settingsService = new SettingsServiceImpl();
    inputUtil inputUtil = new inputUtil();
    List<Product> readProduct = new ArrayList<>();
    List<Product> productWrite = new ArrayList<>();
    List<Product> productUpdate = new ArrayList<>();
    Menu menu = new Menu();

    int currentPage = 1;

    public void displayUI() throws SQLException {
        while (true) {
            // 1. Get current configuration and data
            int displayRows = settingsService.getDisplayRows();
            int totalRecords = productController.countAllProducts();
            int totalPages = (int) Math.ceil((double) totalRecords / displayRows);
            if (totalPages == 0)
                totalPages = 1;
            if (currentPage > totalPages)
                currentPage = totalPages;
            if (currentPage < 1)
                currentPage = 1;

            // 2. Fetch and display table
            List<Product> products = productController.readProduct(currentPage, displayRows);
            DisplayDataTable.displaytTable(products, currentPage, totalPages, totalRecords);

            // 3. Display Menu
            menu.MenuMain();
            String option = inputUtil.option(Color.yellow + "=> Choose an option() : " + Color.reset);

            switch (option.toUpperCase()) {
                // Pagination Commands
                case "N": { // Next Page
                    if (currentPage < totalPages)
                        currentPage++;
                    break;
                }
                case "P": { // Previous Page
                    if (currentPage > 1)
                        currentPage--;
                    break;
                }
                case "F": { // First Page
                    currentPage = 1;
                    break;
                }
                case "L": { // Last Page
                    currentPage = totalPages;
                    break;
                }
                case "G": { // Goto Page
                    int gotoPage = inputUtil.inputId("Enter page number to go: ");
                    if (gotoPage >= 1 && gotoPage <= totalPages) {
                        currentPage = gotoPage;
                    } else {
                        System.out.println(Color.red + "Invalid page number!" + Color.reset);
                    }
                    break;
                }

                // Action Commands
                case "W": {
                    productWrite = productController.writeProduct();
                    break;
                }
                case "R": { // Read (id)
                    int id = inputUtil.inputId("Enter product ID to read: ");
                    productController.searchByIdProduct(id);
                    inputUtil.pressEnterToContinue();
                    break;
                }
                case "U": {
                    int id = inputUtil.inputId("Enter product ID to update: ");
                    productUpdate = productController.updateProduct(id);
                    break;
                }
                case "D": {
                    int id = inputUtil.inputId("Enter product ID to delete: ");
                    productController.searchByIdProduct(id);
                    String choice = inputUtil.InputYN();
                    if (choice.equalsIgnoreCase("y")) {
                        productController.deleteProduct(id);
                    }
                    break;
                }
                case "S": {
                    String name = inputUtil.Inputname("Input name for search: ");
                    productController.searchProduct(name);
                    inputUtil.pressEnterToContinue();
                    break;
                }
                case "SE": {
                    int rows = inputUtil.inputDisplayRows(
                            "Enter number of rows to display (current: " + settingsService.getDisplayRows() + "): ");
                    settingsService.setDisplayRows(rows);
                    currentPage = 1; // Reset to page 1 when row limit changes
                    break;
                }
                case "SA": {
                    if (productWrite.isEmpty() && productUpdate.isEmpty()) {
                        System.out.println(Color.yellow + "⚠ No product to save. Please write or update product first."
                                + Color.reset);
                        break;
                    }
                    System.out.println("'si' for saving insert product and 'su' for update product or 'b' for back");
                    String choose = inputUtil.option("==> Choose an option: ");
                    if (choose.toLowerCase().equals("si")) {
                        productController.saveProduct(productWrite, "si");
                    } else if (choose.toLowerCase().equals("su")) {
                        productController.saveProduct(productUpdate, "su");
                    }
                    break;
                }
                case "UN": {
                    String unOption = inputUtil.option("Choose 'ui' for unsaved insert or 'uu' for unsaved update: ");
                    if (unOption.equalsIgnoreCase("ui")) {
                        productController.unSaveProduct(productWrite, unOption);
                    } else if (unOption.equalsIgnoreCase("uu")) {
                        productController.unSaveProduct(productUpdate, unOption);
                    }
                    inputUtil.pressEnterToContinue();
                    break;
                }
                case "BA": {
                    productController.backupProduct();
                    inputUtil.pressEnterToContinue();
                    break;
                }
                case "RE": {
                    productController.restoreProduct();
                    inputUtil.pressEnterToContinue();
                    break;
                }
                case "E": {
                    System.out.println(Color.blue + "Exiting program..." + Color.reset);
                    System.exit(0);
                }
                default: {
                    System.out.println(Color.red + "✗ Invalid Option..." + Color.reset);
                }
            }
        }
    }
}