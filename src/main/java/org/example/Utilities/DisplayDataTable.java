package org.example.Utilities;

import org.example.Models.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class DisplayDataTable {
    public static void displaytTable(List<Product> products){
        Table table=new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0,30,30);
        table.setColumnWidth(1,30,30);
        table.setColumnWidth(2,30,30);
        table.setColumnWidth(3,30,30);
        table.setColumnWidth(4,30,30);
        table.addCell("ID");
        table.addCell("Name" );
        table.addCell("Utit Price" );
        table.addCell("Qty" );
        table.addCell("Import Date" );
        products.forEach((w)->{
            table.addCell(w.getId()+"");
            table.addCell(w.getName());
            table.addCell(""+w.getPrice());
            table.addCell(w.getQty()+"");
            table.addCell(w.getImport_date()+"");
        });
        System.out.println(table.render());

    }
    public static void displayUnsaveUpdateTable(List<Product> products){

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
            table.addCell(String.valueOf(no++));   // 👈 1,2,3...
            table.addCell(p.getName());
            table.addCell(String.valueOf(p.getPrice()));
            table.addCell(String.valueOf(p.getQty()));
            table.addCell(p.getImport_date());
        }

        System.out.println(table.render());
    }


}
