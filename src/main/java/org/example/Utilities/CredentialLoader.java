package org.example.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialLoader {
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input =
                     CredentialLoader.class.getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (input == null) {
                System.out.println("File not found in classpath");
                return properties;
            }

            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


}
