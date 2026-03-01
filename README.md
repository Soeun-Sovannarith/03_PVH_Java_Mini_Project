# Java Mini Project

## Database Configuration

This project uses environment variables to securely manage database credentials.

### Setup Instructions

1. **Create a `.env` file** in the project root directory
2. Copy the contents from `.env.example`:
   ```bash
   cp .env.example .env
   ```
3. **Edit the `.env` file** with your actual database credentials:
   ```env
   DB_URL=jdbc:postgresql://localhost:5433/mini_project
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   ```

### How It Works

The configuration flow is:
```
.env → CredentialLoader → Properties → DatabaseUtil
```

1. **`.env`** file stores sensitive credentials (not committed to Git)
2. **`CredentialLoader`** reads the `.env` file using the `dotenv-java` library
3. **Properties object** holds the configuration in memory
4. **`DatabaseUtil`** uses the Properties to establish database connections

### Security Notes

- The `.env` file is listed in `.gitignore` and will not be committed to version control
- Never commit actual credentials to the repository
- Use `.env.example` as a template for other developers

### Running the Project

```bash
# Build the project
mvn clean compile

# Package the project
mvn clean package

# Run the application
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## Dependencies

- PostgreSQL JDBC Driver (42.7.10)
- dotenv-java (3.0.0) - For loading environment variables
- text-table-formatter (1.2.4) - For formatting table output
