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

    public void displayUI() throws SQLException {

        while (true) {
            menu.MenuMain();
            String option = inputUtil.option(Color.blue + "Choose an option: " + Color.reset);

            switch (option.toUpperCase()) {

                case "W": {
                    productWrite = productController.writeProduct();
                    break;
                }
<<<<<<< HEAD

                case "R":{
                      readProduct=  productController.readProduct();
                    DisplayDataTable.displayTableWithPagination(readProduct);
//                    DisplayDataTable.displaytTable(readProduct);
=======
                case "R": {
                    int displayRows = settingsService.getDisplayRows();
                    readProduct = productController.readProduct(displayRows);
                    System.out.println(Color.blue + "Displaying " + readProduct.size() + " rows (limit: " + displayRows
                            + ")" + Color.reset);
                    DisplayDataTable.displaytTable(readProduct);
>>>>>>> 2729f42dcb19715039d2532ff244e72e52f6870b
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

                case "SE": {
                    int rows = inputUtil.inputDisplayRows(
                            "Enter number of rows to display (current: " + settingsService.getDisplayRows() + "): ");
                    settingsService.setDisplayRows(rows);
                    System.out.println(Color.green + "✓ Display rows updated! Next time you read (R), it will show "
                            + rows + " rows." + Color.reset);
                    break;
                }
                case "UN": {
                    option = inputUtil.option("Choose 'ui' for unsaved insert or 'uu' for unsaved update: ");
                    if (option.equalsIgnoreCase("ui")) {
                        productController.unSaveProduct(productWrite, option);
                    } else if (option.equalsIgnoreCase("uu")) {
                        productController.unSaveProduct(productUpdate, option);
                    }
                    break;
                }

                case "S": {
                    String name = inputUtil.Inputname("Input name for search: ");
                    productController.searchProduct(name);
                    break;
                }

                case "SA": {
                    if (productWrite.isEmpty() && productUpdate.isEmpty()) {
                        System.out.println(Color.yellow + "⚠ No product to save. Please write or update product first."
                                + Color.reset);
                        break;
                    }
                    System.out.println("'si' for insert | 'su' for update | 'b' for back");
                    String choose = inputUtil.option("=> Choose an option: ");
                    switch (choose.toLowerCase()) {
                        case "si": {
                            if (productWrite.isEmpty()) {
                                System.out.println(Color.red + "✗ No insert data available." + Color.reset);
                                break;
                            }
                            productController.saveProduct(productWrite, "si");
                            break;
                        }

                        case "su": {
                            if (productUpdate.isEmpty()) {
                                System.out.println(Color.red + "✗ No update data available." + Color.reset);
                                break;
                            }
                            productController.saveProduct(productUpdate, "su");
                            break;
                        }

                        case "b": {
                            break;
                        }

                        default: {
                            System.out.println(Color.red + "✗ Invalid Option." + Color.reset);
                        }
                    }
                    break;
                }

                case "B":
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