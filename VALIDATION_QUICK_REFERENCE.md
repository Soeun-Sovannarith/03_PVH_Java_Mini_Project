# Quick Validation Reference Guide

## For Developers Working on Stock Management System

---

## How to Add New Validation

### 1. Add Validation Method to ProductValidation.java

```java
public static boolean validateYourField(String input) {
    if (input == null || input.trim().isEmpty()) {
        System.out.println(Color.red + "Error: Field cannot be empty!" + Color.reset);
        return false;
    }

    // Add your validation logic here
    if (!Pattern.matches("YOUR_REGEX", input.trim())) {
        System.out.println(Color.red + "Error: Invalid format!" + Color.reset);
        return false;
    }

    return true;
}
```

### 2. Add Input Method to inputUtil.java

```java
public String inputYourField(String text) {
    String input;
    while (true) {
        System.out.print(text);
        input = sc.next().trim();

        if (ProductValidation.validateYourField(input)) {
            return input;
        }
    }
}
```

### 3. Use in Service Layer

```java
// In ProductServiceImpl.java
if (!ProductValidation.validateYourField(value)) {
    System.out.println(Color.red + "✗ Validation failed!" + Color.reset);
    return;
}
```

---

## Message Format Standards

### Success Messages
```java
System.out.println(Color.green + "✓ Operation successful!" + Color.reset);
```

### Error Messages
```java
System.out.println(Color.red + "✗ Error: Description" + Color.reset);
```

### Warning Messages
```java
System.out.println(Color.yellow + "⚠ Warning: Description" + Color.reset);
```

### Info Messages
```java
System.out.println(Color.blue + "Info: Description" + Color.reset);
```

---

## Common Regex Patterns

```java
// Name validation (letters, numbers, spaces)
"^[a-zA-Z0-9][a-zA-Z0-9 ]*$"

// Price format (numbers with optional 2 decimal places)
"^[0-9]+(\\.[0-9]{1,2})?$"

// Quantity/Integer (positive integers only)
"^[0-9]+$"

// ID validation (positive integers)
"^[0-9]+$"

// Alphabetic only (menu options)
"^[a-zA-Z]+$"

// Yes/No input
"^[yYnN]$"



---

## Validation Checklist

Before committing code, ensure:

- [ ] All user inputs are validated
- [ ] Error messages are user-friendly
- [ ] Success messages confirm operations
- [ ] Color coding is consistent
- [ ] Validation loops allow retry
- [ ] Database operations have validation
- [ ] Edge cases are handled
- [ ] No raw exceptions shown to user
- [ ] SQL injection prevention in place

---

## Available Validation Methods

### ProductValidation Class

| Method | Purpose | Returns |
|--------|---------|---------|
| `validateName(String)` | Validates product name | boolean |
| `validatePrice(double)` | Validates price value | boolean |
| `validatePriceFormat(String)` | Validates price string | boolean |
| `validateQuantity(int)` | Validates quantity value | boolean |
| `validateQuantityFormat(String)` | Validates quantity string | boolean |
| `validateId(int)` | Validates ID value | boolean |
| `validateIdFormat(String)` | Validates ID string | boolean |
| `validateOption(String)` | Validates menu option | boolean |
| `validateYesNo(String)` | Validates Y/N input | boolean |
| `validateDisplayRows(int)` | Validates row count | boolean |
| `validateDateFormat(String)` | Validates date format | boolean |
| `validateProduct(String, double, int)` | Validates complete product | boolean |

### inputUtil Class

| Method | Purpose | Returns |
|--------|---------|---------|
| `Inputname(String)` | Get validated product name | String |
| `inputPrice(String)` | Get validated price | double |
| `qty(String)` | Get validated quantity | int |
| `option(String)` | Get validated menu option | String |
| `InputYN()` | Get validated yes/no | String |
| `inputId(String)` | Get validated product ID | int |
| `inputDisplayRows(String)` | Get validated row count | int |

---

## Error Handling Pattern

```java
try {
    // Your database operation

} catch (SQLException e) {
    System.out.println(Color.red + "✗ Database Error: " + e.getMessage() + Color.reset);
    throw e; // Re-throw if needed
} catch (Exception e) {
    System.out.println(Color.red + "✗ Unexpected Error: " + e.getMessage() + Color.reset);
    e.printStackTrace();
}
```

---

## Best Practices

1. **Always Validate Before Database Operations**
   - Check data validity before INSERT/UPDATE/DELETE
   - Validate IDs before queries

2. **User-Friendly Messages**
   - Don't show technical stack traces to users
   - Provide actionable feedback
   - Use color coding consistently

3. **Input Retry Loops**
   - Loop until valid input received
   - Don't crash on invalid input
   - Clear error messages on each attempt

4. **Trim All String Inputs**
   - Remove leading/trailing whitespace
   - Prevents "invisible" invalid data

5. **Test Edge Cases**
   - Empty strings
   - Maximum values
   - Minimum values
   - Special characters
   - Whitespace

---

## Testing Your Validation

### Manual Test Template

```
Test: [Validation Name]
Input: [Test Input]
Expected: [Expected Result]
Actual: [Actual Result]
Status: Pass/Fail
```

### Example:
```
Test: Product Name Validation
Input: "Test@Product"
Expected: Error message about special characters
Actual: "Error: Product name can only contain letters, numbers, and spaces!"
Status: Pass ✅
```

---

## Common Pitfalls to Avoid

❌ **Don't:**
- Show stack traces to end users
- Accept empty/null without validation
- Use generic error messages like "Error"
- Forget to trim string inputs
- Skip validation on "trusted" data

✅ **Do:**
- Validate all user inputs
- Provide specific error messages
- Use color coding for clarity
- Handle exceptions gracefully
- Test edge cases thoroughly

---

## Performance Considerations

1. **Regex Compilation**
   - Regex patterns are compiled once as constants
   - Very efficient for repeated validation

2. **Database Connections**
   - Always use try-with-resources
   - Connections are auto-closed

3. **Validation Order**
   - Cheapest checks first (null, empty)
   - Expensive checks last (regex, database)

---

## Documentation Links

- **Full Validation Summary:** `VALIDATION_SUMMARY.md`
- **Testing Guide:** `VALIDATION_TESTING.md`
- **Main README:** `README.md`

---

**Last Updated:** March 4, 2026
**Maintained By:** Development Team
**Version:** 1.0
