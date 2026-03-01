package org.example;
import org.example.Utilities.DatabaseUtil;
import java.sql.*;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
class ReadProduct {
    public void readProduct() {
        CellStyle center = new CellStyle(HorizontalAlign.CENTER);
        Table t = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                ShownBorders.ALL);
        t.setColumnWidth(0, 24, 24);
        t.setColumnWidth(1, 24, 26);
        t.setColumnWidth(2, 24, 26);
        t.setColumnWidth(2, 24, 26);
        t.addCell("ID",center);
        t.addCell("NAME",center);
        t.addCell("UNIT PRICE",center);
        t.addCell("QTY",center);
        String read_product = "SELECT * FROM test";
        //query data
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(read_product);
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getInt("price");
                int qty=rs.getInt("qty");
                t.addCell(String.valueOf(id),center);
                t.addCell(name,center);
                t.addCell(String.valueOf(price),center);
                t.addCell(String.valueOf(qty),center);
            }
            System.out.println("Query User successfully!");
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        System.out.println(t.render());
    }
}
