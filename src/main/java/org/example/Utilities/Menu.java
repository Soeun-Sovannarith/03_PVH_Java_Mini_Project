package org.example.Utilities;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class Menu {
    public void MenuPagination(){
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 30, 30);
        table.setColumnWidth(3, 30, 30);
        table.setColumnWidth(4, 30, 30);
        // Merge all 5 columns for the welcome message and center the text
        String welcomeText = "Welcome to Java MiniProject";
        table.addCell(centerText(welcomeText, 150), 5);

        table.addCell(centerText("CHOOSING:", 30));
        table.addCell(centerText("N. Next Page", 30));
        table.addCell(centerText("P. Previous Page", 30));
        table.addCell(centerText("F. First Page", 30));
        table.addCell(centerText("G. Goto Page", 30));

        // Print the table
        System.out.println(table.render());
    }

    public void MenuMain(){
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 30, 30);
        table.setColumnWidth(3, 30, 30);
        table.setColumnWidth(4, 30, 30);
        // Feature selection row 1
        table.addCell(centerText(Color.yellow+"CHOOSE FOR USE FEATURE"+ Color.reset, 150), 5);

        // Feature selection row 2
        table.addCell(centerText(Color.blue+"W)"+ Color.reset+" Write", 30));
        table.addCell(centerText("R) Read (id)", 30));
        table.addCell(centerText("U) Update", 30));
        table.addCell(centerText("D) Delete", 30));
        table.addCell(centerText("S) Search (name)", 30));

        // Feature selection row 3
        table.addCell(centerText("Se) Set rows", 30));
        table.addCell(centerText("Sa) Save", 30));
        table.addCell(centerText("Un) Unsaved", 30));
        table.addCell(centerText("Ba) Backup", 30));
        table.addCell(centerText("Re) Restore", 30));

        table.addCell(centerText("E) Exit", 150), 5);

        System.out.println(table.render());
    }

    // Function to center text
    public String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            return " ".repeat(padding) + text + " ".repeat(padding);
        } else {
            return text;
        }
    }
}

