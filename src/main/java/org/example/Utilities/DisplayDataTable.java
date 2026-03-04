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



}
