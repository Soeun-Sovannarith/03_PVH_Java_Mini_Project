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
