package org.example.Utilities;

import org.example.Validation.ProductValidation;
import java.util.Scanner;

public class inputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public String address(String text) {
        System.out.print(text);
        return sc.nextLine();
    }

    public String InputYN() {
        String answer;
        while (true) {
            System.out.print(Color.green + "=> Do you want to continue? (y/n): " + Color.reset);
            answer = sc.nextLine().trim();
            if (ProductValidation.validateYesNo(answer)) {
                break;
            }
        }
        return answer.toLowerCase();
    }

    public int qty(String text) {
        String number;
        while (true) {
            System.out.print(text);
            number = sc.nextLine().trim();
            if (ProductValidation.validateQuantityFormat(number)) {
                int qty = Integer.parseInt(number);
                if (ProductValidation.validateQuantity(qty)) {
                    return qty;
                }
            }
        }
    }

    public String Inputname(String text) {
        String name;
        while (true) {
            System.out.print(text);
            name = sc.nextLine().trim();
            if (ProductValidation.validateName(name)) {
                break;
            }
        }
        return name;
    }

    public String option(String text) {
        String opt;
        while (true) {
            System.out.print(text);
            opt = sc.nextLine().trim();
            if (ProductValidation.validateOption(opt)) {
                break;
            }
        }
        return opt;
    }

    public double inputPrice(String text) {
        String balance;
        while (true) {
            System.out.print(text);
            balance = sc.nextLine().trim();
            if (ProductValidation.validatePriceFormat(balance)) {
                double price = Double.parseDouble(balance);
                if (ProductValidation.validatePrice(price)) {
                    return price;
                }
            }
        }
    }

    public int inputId(String text) {
        String idStr;
        while (true) {
            System.out.print(text);
            idStr = sc.nextLine().trim();
            if (ProductValidation.validateIdFormat(idStr)) {
                int id = Integer.parseInt(idStr);
                if (ProductValidation.validateId(id)) {
                    return id;
                }
            }
        }
    }

    public int inputDisplayRows(String text) {
        String rowsStr;
        while (true) {
            System.out.print(text);
            rowsStr = sc.nextLine().trim();
            if (ProductValidation.validateQuantityFormat(rowsStr)) {
                int rows = Integer.parseInt(rowsStr);
                if (ProductValidation.validateDisplayRows(rows)) {
                    return rows;
                }
            }
        }
    }
}
