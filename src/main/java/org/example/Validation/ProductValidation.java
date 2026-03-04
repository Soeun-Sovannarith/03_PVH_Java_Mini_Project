package org.example.Validation;

import org.example.Utilities.Color;
import java.util.regex.Pattern;

public class ProductValidation {

    // Regex Constants
    public static final String NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9 ]*$";
    public static final String PRICE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public static final String QTY_REGEX = "^[0-9]{1,5}$"; // Up to 5 digits (99,999)
    public static final String ID_REGEX = "^[0-9]+$";
    public static final String ALPHA_REGEX = "^[a-zA-Z]+$";
    public static final String YES_NO_REGEX = "^[yYnN]$";
    public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    // Business Constraints
    public static final double MAX_PRICE = 100000.0; // Maximum price: 100,000
    public static final double MIN_PRICE = 0.01; // Minimum price: 1 cent
    public static final int MAX_QUANTITY = 10000; // Maximum quantity: 10,000 units
    public static final int MIN_QUANTITY = 1; // Minimum quantity: 1 unit

    // Validation Methods

    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println(Color.red + " Error: Product name cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(NAME_REGEX, name.trim())) {
            System.out.println(Color.red
                    + " Error: Invalid product name! (Letters, numbers, and spaces allowed, but MUST start with a letter)"
                    + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validatePriceFormat(String priceStr) {
        if (priceStr == null || priceStr.trim().isEmpty()) {
            System.out.println(Color.red + " Error: Price cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(PRICE_REGEX, priceStr.trim())) {
            System.out.println(Color.red + " Error: Invalid price format! (Example: 10 or 10.50)" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validatePrice(double price) {
        if (price < MIN_PRICE) {
            System.out.println(Color.red + " Error: Price must be at least $" + MIN_PRICE + "!" + Color.reset);
            return false;
        }
        if (price > MAX_PRICE) {
            System.out.println(Color.red + " Error: Price cannot exceed $" + MAX_PRICE + "!" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateQuantityFormat(String qtyStr) {
        if (qtyStr == null || qtyStr.trim().isEmpty()) {
            System.out.println(Color.red + " Error: Quantity cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(QTY_REGEX, qtyStr.trim())) {
            System.out.println(Color.red + " Error: Invalid quantity! (Positive integers only)" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateQuantity(int qty) {
        if (qty < MIN_QUANTITY) {
            System.out.println(Color.red + " Error: Quantity must be at least " + MIN_QUANTITY + "!" + Color.reset);
            return false;
        }
        if (qty > MAX_QUANTITY) {
            System.out.println(Color.red + " Error: Quantity cannot exceed " + MAX_QUANTITY + " units!" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateIdFormat(String idStr) {
        if (idStr == null || idStr.trim().isEmpty()) {
            System.out.println(Color.red + " Error: ID cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(ID_REGEX, idStr.trim())) {
            System.out.println(Color.red + " Error: Invalid ID format! (Positive integers only)" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateId(int id) {
        if (id <= 0) {
            System.out.println(Color.red + " Error: ID must be a positive integer!" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateOption(String option) {
        if (option == null || option.trim().isEmpty()) {
            System.out.println(Color.red + " Error: Option cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(ALPHA_REGEX, option.trim())) {
            System.out.println(Color.red + " Error: Invalid option! (Letters only)" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateYesNo(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println(Color.red + " Error: Input cannot be empty!" + Color.reset);
            return false;
        }
        if (!Pattern.matches(YES_NO_REGEX, input.trim())) {
            System.out.println(Color.red + " Error: Invalid input! (Enter 'y' or 'n')" + Color.reset);
            return false;
        }
        return true;
    }

    public static boolean validateDisplayRows(int rows) {
        if (rows <= 0) {
            System.out.println(Color.red + " Error: Rows must be greater than zero!" + Color.reset);
            return false;
        }
        return true;
    }


}
