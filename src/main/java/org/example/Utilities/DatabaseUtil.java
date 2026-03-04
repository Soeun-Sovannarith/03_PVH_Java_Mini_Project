package org.example.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtil {

    public static Connection getConnection(){
        try{
            Properties properties = CredentialLoader.loadProperties();
            Connection conn = DriverManager
                    .getConnection(
                            properties.getProperty("url"),
                            properties.getProperty("name"),
                            properties.getProperty("password")
                    );


            if(conn.isValid(1)){
                return conn;
            }else {
               throw new RuntimeException("Unable to connect");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
