package org.example.Services;

import org.example.Utilities.DatabaseUtil;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SettingsServiceImpl implements SettingService{

    @Override
    public int getDisplayRows() {
        String sql = "SELECT setting_value FROM app_settings WHERE setting_key = 'display_rows'";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pt = con.prepareStatement(sql);
             ResultSet rs = pt.executeQuery()) {

            if (rs.next()) {
                return Integer.parseInt(rs.getString("setting_value"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading display rows setting: " + e.getMessage());
        }
        return 10; // Default fallback
    }



    @Override
    public void setDisplayRows(int rows) {
        String sql = "INSERT INTO app_settings (setting_key, setting_value) VALUES ('display_rows', ?) " +
                "ON CONFLICT (setting_key) DO UPDATE SET setting_value = EXCLUDED.setting_value, updated_at = CURRENT_TIMESTAMP";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {

            pt.setString(1, String.valueOf(rows));
            pt.executeUpdate();
            System.out.println("Display rows set to: " + rows);

        } catch (SQLException e) {
            System.out.println("Error saving display rows setting: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
