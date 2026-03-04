package org.example.Utilities;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class Menu {
    public void MenuPagination() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);
        table.setColumnWidth(2, 30, 30);
        table.setColumnWidth(3, 30, 30);
        table.setColumnWidth(4, 30, 30);

        String welcomeText = "Welcome to Java MiniProject";
        table.addCell(centerText(welcomeText, 150), 5);

        table.addCell(centerText("CHOOSING:", 30));
        table.addCell(centerText("N. Next Page", 30));
        table.addCell(centerText("P. Previous Page", 30));
        table.addCell(centerText("F. First Page", 30));
        table.addCell(centerText("G. Goto Page", 30));

        System.out.println(table.render());
    }

    public void MenuMain() {
        System.out.println(
                "                                     " + Color.cyan + "---------- Menu ----------" + Color.reset);

        Table menuTable = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.NONE);
        menuTable.setColumnWidth(0, 20, 20);
        menuTable.setColumnWidth(1, 25, 25);
        menuTable.setColumnWidth(2, 20, 20);
        menuTable.setColumnWidth(3, 20, 20);
        menuTable.setColumnWidth(4, 20, 20);

        menuTable.addCell(Color.green + "N." + Color.reset + " Next Page");
        menuTable.addCell(Color.green + "P." + Color.reset + " Previous Page");
        menuTable.addCell(Color.green + "F." + Color.reset + " First Page");
        menuTable.addCell(Color.green + "L." + Color.reset + " Last Page");
        menuTable.addCell(Color.green + "G." + Color.reset + " Goto");

        System.out.println(menuTable.render());

        Table actionTable = new Table(6, BorderStyle.UNICODE_BOX, ShownBorders.NONE);
        actionTable.setColumnWidth(0, 15, 15);
        actionTable.setColumnWidth(1, 20, 20);
        actionTable.setColumnWidth(2, 15, 15);
        actionTable.setColumnWidth(3, 15, 15);
        actionTable.setColumnWidth(4, 25, 25);
        actionTable.setColumnWidth(5, 15, 15);

        actionTable.addCell(Color.green + "W) " + Color.reset + "Write");
        actionTable.addCell(Color.green + "R) " + Color.reset + "Read (id)");
        actionTable.addCell(Color.green + "U) " + Color.reset + "Update");
        actionTable.addCell(Color.green + "D) " + Color.reset + "Delete");
        actionTable.addCell(Color.green + "S) " + Color.reset + "Search (name)");
        actionTable.addCell(Color.green + "Se) " + Color.reset + "Set rows");

        actionTable.addCell(Color.green + "sa)" + Color.reset + " Save");
        actionTable.addCell(Color.green + "Un)" + Color.reset + " Unsaved");
        actionTable.addCell(Color.green + "Ba)" + Color.reset + " Backup");
        actionTable.addCell(Color.green + "Re)" + Color.reset + " Restore");
        actionTable.addCell(Color.green + "E) " + Color.reset + " Exit");
        actionTable.addCell("");

        System.out.println(actionTable.render());
        System.out.println(
                "      " + "---------------------------------------------------------------------------------------");
    }

    public String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        if (padding > 0) {
            return " ".repeat(padding) + text + " ".repeat(padding);
        } else {
            return text;
        }
    }
}
