package org.example.Views;

import org.example.Controller.ProductController;
import org.example.Models.Product;
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
                      readProduct=  productController.readProduct();
                    DisplayDataTable.displaytTable(readProduct);
                    break;
                }

                case "D": {
                    System.out.println("Enter product ID to delete: ");
                    int id = scanner.nextInt();
                    productController.deleteProduct(id);
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

                default: {
                    System.out.println("Invalid Option...");
                }
            }
        }
    }
}