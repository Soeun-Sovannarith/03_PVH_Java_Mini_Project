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
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    ProductController productController = new ProductController();
    SettingService settingsService = new SettingsServiceImpl();
    inputUtil inputUtil = new inputUtil();
    List<Product> readProduct=new ArrayList<>();
    List<Product> searchProduct=new ArrayList<>();
    List<Product> productWrite = new ArrayList<>();
    Menu menu = new Menu();

    public void displayUI() throws SQLException {

        while (true) {

            menu.MenuMain();
            String option = inputUtil.option(Color.blue + "Choose an option: " + Color.reset);

            switch (option.toUpperCase()) {

                case "W": {
                    productWrite = productController.writeProduct();
                    break;
                }
                case "R":{
                    int displayRows = settingsService.getDisplayRows();
                    readProduct = productController.readProduct(displayRows);
                    System.out.println(Color.blue + "Displaying " + readProduct.size() + " rows (limit: " + displayRows + ")" + Color.reset);
                    DisplayDataTable.displaytTable(readProduct);
                    break;
                }

                case "D": {
                    System.out.println("Enter product ID to delete: ");
                    int id = scanner.nextInt();
                    productController.deleteProduct(id);
                    break;
                }

                case "SE": {
                    System.out.print("Enter number of rows to display (current: " + settingsService.getDisplayRows() + "): ");
                    try {
                        int rows = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (rows > 0) {
                            settingsService.setDisplayRows(rows);
                            System.out.println(Color.green + "Display rows updated! Next time you read (R), it will show " + rows + " rows." + Color.reset);
                        } else {
                            System.out.println(Color.red + "Please enter a positive number." + Color.reset);
                        }
                    } catch (Exception e) {
                        scanner.nextLine(); // clear buffer
                        System.out.println(Color.red + "Invalid input. Please enter a number." + Color.reset);
                    }
                    break;
                }

                case "S": {
                    System.out.print("Input name for search: ");

                    String name = scanner.nextLine().trim();
                    productController.searchProduct(name);
                    break;
                }

                case "SA": {

                    if (productWrite.isEmpty()) {
                        System.out.println("No product to save. Please write product first.");
                        break;
                    }

                    System.out.println("'si' for insert | 'su' for update | 'b' for back");
                    String choose = inputUtil.option("=> Choose an option: ");

                    switch (choose.toLowerCase()) {

                        case "si": {
                            productController.saveProduct(productWrite, "si");
                            break;
                        }

                        case "su": {
//                            int id =
//                            productWrite.get(0).setId(id);
//                            productController.saveProduct(productWrite, "su");
//                            break;
                        }

                        case "b": {
                            break;
                        }

                        default: {
                            System.out.println("Invalid Input...");
                        }
                    }
                    break;
                }


                case "B": {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
                case"E": {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }

                default: {
                    System.out.println("Invalid Option...");
                }
            }
        }
    }
}