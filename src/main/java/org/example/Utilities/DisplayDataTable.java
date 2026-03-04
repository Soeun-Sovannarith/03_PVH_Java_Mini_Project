package org.example.Utilities;

import org.example.Models.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

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
        table.addCell("Unit Price" );
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

    public static void displayTableWithPagination(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        int pageSize = 5;
        int currentPage = 0;
        int totalRecord = products.size();
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);

        while (true) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, totalRecord);

            List<Product> pageList = products.subList(start, end);
            System.out.println("\n--- Page " + (currentPage + 1) + " of " + totalPage + " ---");
            displaytTable(pageList);
            System.out.println("F. First\tN. Next\tP. Previous\tL. Last\tE. Exit");
            System.out.print("==> Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "F" -> currentPage = 0;
                case "N" -> {
                    if (currentPage < totalPage - 1) currentPage++;
                }
                case "P" -> {
                    if (currentPage > 0) currentPage--;
                }
                case "T" -> currentPage = totalPage - 1;
                case "E" -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

}
