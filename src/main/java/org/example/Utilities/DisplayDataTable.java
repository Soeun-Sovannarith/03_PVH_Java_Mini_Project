package org.example.Utilities;

import org.example.Models.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class DisplayDataTable {
    public static void displaytTable(List<Product> products, int currentPage, int totalPages, int totalRecords) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 10, 10);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 20, 20);
        table.setColumnWidth(3, 15, 15);
        table.setColumnWidth(4, 25, 25);

        // Header
        table.addCell(Color.blue + "ID" + Color.reset);
        table.addCell(Color.blue + "Name" + Color.reset);
        table.addCell(Color.blue + "Unit Price" + Color.reset);
        table.addCell(Color.blue + "Qty" + Color.reset);
        table.addCell(Color.blue + "Import Date" + Color.reset);

        // Data
        products.forEach((p) -> {
            table.addCell(Color.green + p.getId() + Color.reset);
            table.addCell(p.getName());
            table.addCell(String.valueOf(p.getPrice()));
            table.addCell(String.valueOf(p.getQty()));
            table.addCell(p.getImport_date());
        });

        // Footer Row for Pagination
        String footerPage = "Page : " + Color.yellow + currentPage + Color.reset + " of " + Color.red + totalPages
                + Color.reset;
        String footerTotal = "Total Record : " + Color.green + totalRecords + Color.reset;

        table.addCell(footerPage, 3); // Spans 3 columns
        table.addCell(footerTotal, 2); // Spans 2 columns

        System.out.println(table.render());
    }

    public static void displaytTable(List<Product> products) {
        displaytTable(products, 1, 1, products.size());
    }

    public static void displayUnsaveUpdateTable(List<Product> products) {

        if (products == null || products.isEmpty()) {
            System.out.println("No unsave update data to display.");
            return;
        }

        // ✅ SAME SIZE AS displaytTable (5 columns)
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        table.setColumnWidth(0, 30, 30); // No
        table.setColumnWidth(1, 30, 30); // Name
        table.setColumnWidth(2, 30, 30); // Unit Price
        table.setColumnWidth(3, 30, 30); // Qty
        table.setColumnWidth(4, 30, 30); // Import Date

        // ✅ Header (NO ID)
        table.addCell("No");
        table.addCell("Name");
        table.addCell("Unit Price");
        table.addCell("Qty");
        table.addCell("Import Date");

        int no = 1; // Unsave index starts from 1

        for (Product p : products) {
            table.addCell(String.valueOf(no++)); // 👈 1,2,3...
            table.addCell(p.getName());
            table.addCell(String.valueOf(p.getPrice()));
            table.addCell(String.valueOf(p.getQty()));
            table.addCell(p.getImport_date());
        }

        System.out.println(table.render());
    }

}
