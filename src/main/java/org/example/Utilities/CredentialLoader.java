package org.example.Utilities;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class CredentialLoader {
    private static Dotenv dotenv;

    static {
        try {
            // Load .env file from the project root
            dotenv = Dotenv.configure()
                    .directory("./")
                    .ignoreIfMissing()
                    .load();
        } catch (Exception e) {
            System.err.println("Error loading .env file: " + e.getMessage());
        }
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try {
            if (dotenv == null) {
                System.out.println(".env file not found or could not be loaded");
                return properties;
            }

            // Load environment variables into properties
            String url = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");

            if (url != null) properties.setProperty("url", url);
            if (username != null) properties.setProperty("name", username);
            if (password != null) properties.setProperty("password", password);

        } catch (Exception e) {
            System.err.println("Error loading properties from .env: " + e.getMessage());
        }

        return properties;
    }

}
