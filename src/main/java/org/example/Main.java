package org.example;

import org.example.Utilities.DatabaseUtil;
import org.example.Views.UI;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        UI ui=new UI();
        ui.displayUI();
    }

}

