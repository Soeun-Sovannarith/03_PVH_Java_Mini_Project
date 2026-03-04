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
### Script for Database 
stock table

```sql  
CREATE TABLE IF NOT EXISTS stock (
id INT GENERATED DEFAULT AS IDENTITY PRIMARY KEY,
name VARCHAR(250) NOT NULL,
price DECIMAL(10,2) NOT NULL,
qty INT NOT NULL,
import_date  VARCHAR(250) NOT NULL
);


``` 


app setting for row recovery
```sql  
CREATE TABLE IF NOT EXISTS app_settings (
    id SERIAL PRIMARY KEY,
    setting_key VARCHAR(100) UNIQUE NOT NULL,
    setting_value VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

``` 


## Dependencies

- PostgreSQL JDBC Driver (42.7.10)
- dotenv-java (3.0.0) - For loading environment variables
- text-table-formatter (1.2.4) - For formatting table output
