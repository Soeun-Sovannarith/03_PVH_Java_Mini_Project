package org.example;

import org.example.Utilities.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
public void update(){
    String updateName = " update test set name = ? where name = ?";
    try (Connection conn = DatabaseUtil.getConnection();
    ) {
        conn.setAutoCommit(false);
        try (PreparedStatement pStm = conn.prepareStatement(updateName);
             PreparedStatement pStm2 = conn.prepareStatement(updateName)
            ) {
            pStm.setString(1, "Kon khmer");
            pStm.setString(2, "Hello");
            pStm2.setString(1, "Group3");
            pStm2.setString(2, "JDBC");
            pStm.executeUpdate();
            pStm2.executeUpdate();
            conn.commit();
            System.out.println("Update success!");
        } catch (SQLException e) {
            conn.rollback();
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
