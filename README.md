# BTCbit.net Automated Testing Project

This project contains automated tests for the BTCbit.net cryptocurrency exchange platform using Selenium WebDriver and JUnit 5.

## Project Structure

```
src/
├── main/java/com/btcbit/test/pages/
│   ├── BasePage.java         # Base page with common methods
│   ├── HomePage.java         # Home page interactions
│   ├── LoginPage.java        # Login page interactions
│   ├── ProfilePage.java      # Profile page validations
│   └── SignupPage.java       # Registration page interactions
│   └── ContactsPage.java     # Contacts page interactions
└── test/java/com/btcbit/test/
    └── BtcBitTest.java       # Test cases implementation
```

## Test Cases

The project includes the following test cases:

1. `testClientRegistration`
   - Attempts to register a new user
   - Note: Full registration validation is disabled due to security measures (CAPTCHA, email validation)
   - Verifies navigation to registration page
   - Fills registration form with generated test data

2. `testFailedLogin`
   - Verifies login failure with invalid credentials
   - Uses valid email with random invalid password
   - Validates error message display
   - Ensures proper error handling

3. `testSuccessfulLogin`
   - Verifies successful login with valid credentials
   - Validates redirect to profile page
   - Checks profile information display
   - Ensures proper authentication flow

4. `testContactsPage`
   - Verifies Contact page data 

## Prerequisites

- Java 11 or higher
- Maven
- Chrome browser
- ChromeDriver (automatically managed by WebDriverManager)

## Dependencies

- Selenium WebDriver
- JUnit 5
- WebDriverManager
- JavaFaker (for test data generation)

## Running Tests

To run the tests, use one of the following commands:

```bash
# Run all tests
mvn test

# Run a specific test
mvn test -Dtest=BtcBitTest#testSuccessfulLogin
```

## Page Object Model

The project follows the Page Object Model (POM) design pattern:

- Each page has its own class with related elements and methods
- `BasePage` provides common functionality
- Page classes handle element interactions and validations
- Test classes focus on test scenarios and assertions

## Security Considerations

- Registration automation is limited due to security measures:
  - Complex email validation rules
  - CAPTCHA protection
  - Additional security mechanisms
- Manual testing is required for complete registration validation

## Best Practices Implemented

1. Explicit waits for element interactions
2. Multiple locator strategies for reliability
3. Detailed logging and error reporting
4. Clean code structure with proper separation of concerns
5. Comprehensive test data generation
6. Proper exception handling and validation

## Notes

- The project uses ChromeDriver by default
- Tests can be run in headless mode (commented out in options)
- Window size and notifications are configured in test setup
- Test data is generated dynamically using JavaFaker 