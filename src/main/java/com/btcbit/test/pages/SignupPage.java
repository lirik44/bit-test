package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Scanner;

public class SignupPage extends BasePage {
    // Registration page element locators
    private final By emailField = By.cssSelector("input[type='email']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By confirmPasswordField = By.cssSelector("input[id*='confirm']");
    private final By signUpButton = By.cssSelector("button[type='submit']");
    
    // Multiple locator strategies for the agreement checkbox
    // Exact locator found through dev tools
    private final By agreeCheckboxExact = By.cssSelector(".input-bool_check__Nov61");
    private final By agreeCheckboxExactSvg = By.cssSelector(".input-bool_check__Nov61 svg");
    
    // Primary checkbox locator
    private final By agreeCheckbox = By.cssSelector("input[type='checkbox']");
    
    // Alternative checkbox locators
    private final By agreeCheckboxById = By.cssSelector("input[id*='agree'], input[id*='terms'], input[id*='consent']");
    private final By agreeCheckboxByName = By.cssSelector("input[name*='agree'], input[name*='terms'], input[name*='consent']");
    private final By agreeCheckboxByClass = By.cssSelector("input[class*='agree'], input[class*='terms'], input[class*='consent']");
    private final By agreeCheckboxByXPath = By.xpath("//input[@type='checkbox'][contains(@id, 'agree') or contains(@name, 'agree') or contains(@class, 'agree') or contains(@id, 'terms') or contains(@name, 'terms')]");
    private final By agreeCheckboxByLabel = By.xpath("//label[contains(text(), 'agree') or contains(text(), 'terms') or contains(text(), 'consent')]/input[@type='checkbox'] | //label[contains(text(), 'agree') or contains(text(), 'terms') or contains(text(), 'consent')]/preceding-sibling::input[@type='checkbox'] | //label[contains(text(), 'agree') or contains(text(), 'terms') or contains(text(), 'consent')]/following-sibling::input[@type='checkbox']");
    
    // CAPTCHA locators
    private final By captchaFrame = By.cssSelector("iframe[title*='recaptcha'], iframe[src*='recaptcha'], iframe[name*='recaptcha']");
    
    // Email validation error locators with multiple strategies
    private final By emailValidationError = By.xpath("//div[text()='Email not valid! Please try other']");
    private final By emailValidationErrorContains = By.xpath("//div[contains(text(), 'Email not valid')]");
    private final By emailValidationErrorByClass = By.cssSelector("div.bg-error");
    
    private final String signUpUrl = "https://btcbit.net/sign_up/";
    private final String EMAIL_DOMAIN = "@test.com";
    
    // Flag to enable/disable manual CAPTCHA input
    private boolean allowManualCaptchaInput = false;
    
    public SignupPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Verifies that we are on the sign up page
     * @return true if we are on the correct page
     */
    public boolean verifyPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(signUpUrl));
            return driver.getCurrentUrl().equals(signUpUrl);
        } catch (Exception e) {
            System.out.println("Failed to verify sign up page URL: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Generates email using first 3 characters from first name and last name
     * @param firstName First name
     * @param lastName Last name
     * @return Generated email in format: first3last3@test.com
     */
    public String generateEmail(String firstName, String lastName) {
        // Take first 3 characters from first name
        String first3 = firstName.length() >= 3 ? firstName.substring(0, 3) : firstName;
        
        // Take first 3 characters from last name
        String last3 = lastName.length() >= 3 ? lastName.substring(0, 3) : lastName;
        
        return (first3 + last3).toLowerCase() + EMAIL_DOMAIN;
    }
    
    /**
     * Sets the flag to allow manual CAPTCHA input
     * @param allow true to allow manual input, false to skip
     */
    public void setAllowManualCaptchaInput(boolean allow) {
        this.allowManualCaptchaInput = allow;
    }
    
    /**
     * Enters email in the registration field
     * @param email Email for registration
     */
    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }
    
    /**
     * Enters password in the registration field
     * @param password Password for registration
     */
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }
    
    /**
     * Enters password confirmation in the registration field
     * @param password Password confirmation for registration
     */
    public void enterConfirmPassword(String password) {
        sendKeys(confirmPasswordField, password);
    }
    
    /**
     * Checks the "I agree" checkbox
     * Tries multiple locator strategies to find and click the checkbox
     */
    public void checkAgreeCheckbox() {
        // First try the exact locator found through dev tools
        try {
            System.out.println("Trying to click checkbox with exact locator from dev tools");
            scrollAndClick(agreeCheckboxExact);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox with exact locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying to click checkbox SVG with exact locator from dev tools");
            scrollAndClick(agreeCheckboxExactSvg);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox SVG with exact locator: " + e.getMessage());
        }
        
        // Try JavaScript click for exact locator
        try {
            System.out.println("Trying JavaScript click with exact locator from dev tools");
            clickWithJavaScript(agreeCheckboxExact);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with exact locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with exact SVG locator from dev tools");
            clickWithJavaScript(agreeCheckboxExactSvg);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with exact SVG locator: " + e.getMessage());
        }
        
        // Attempt to find and click the checkbox using different locators
        try {
            System.out.println("Trying to click checkbox with primary locator");
            scrollAndClick(agreeCheckbox);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox with primary locator: " + e.getMessage());
        }
        
        // Try alternative locators
        try {
            System.out.println("Trying to click checkbox by ID");
            scrollAndClick(agreeCheckboxById);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox by ID: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying to click checkbox by name");
            scrollAndClick(agreeCheckboxByName);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox by name: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying to click checkbox by class");
            scrollAndClick(agreeCheckboxByClass);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox by class: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying to click checkbox by XPath");
            scrollAndClick(agreeCheckboxByXPath);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox by XPath: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying to click checkbox by label");
            scrollAndClick(agreeCheckboxByLabel);
            return;
        } catch (Exception e) {
            System.out.println("Failed to check the agreement checkbox by label: " + e.getMessage());
        }
        
        // If all locators failed, try JavaScript for all locators
        try {
            System.out.println("Trying JavaScript click with primary locator");
            clickWithJavaScript(agreeCheckbox);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with primary locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with ID locator");
            clickWithJavaScript(agreeCheckboxById);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with ID locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with name locator");
            clickWithJavaScript(agreeCheckboxByName);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with name locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with class locator");
            clickWithJavaScript(agreeCheckboxByClass);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with class locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with XPath locator");
            clickWithJavaScript(agreeCheckboxByXPath);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with XPath locator: " + e.getMessage());
        }
        
        try {
            System.out.println("Trying JavaScript click with label locator");
            clickWithJavaScript(agreeCheckboxByLabel);
            return;
        } catch (Exception e) {
            System.out.println("Failed JavaScript click with label locator: " + e.getMessage());
            System.out.println("All attempts to check the agreement checkbox failed");
        }
    }
    
    /**
     * Checks if CAPTCHA is present on the page
     * @return true if CAPTCHA is detected, false otherwise
     */
    public boolean isCaptchaPresent() {
        try {
            return isElementDisplayed(captchaFrame);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Waits for manual CAPTCHA input from the user
     * @param timeoutSeconds maximum wait time in seconds
     * @return true if user confirmed CAPTCHA input, false if timeout occurred
     */
    public boolean waitForManualCaptchaInput(int timeoutSeconds) {
        System.out.println("=================================================================");
        System.out.println("CAPTCHA detected! Manual input required.");
        System.out.println("Please solve the CAPTCHA in the open browser.");
        System.out.println("You have " + timeoutSeconds + " seconds.");
        System.out.println("After solving the CAPTCHA, press Enter in the console.");
        System.out.println("=================================================================");
        
        Scanner scanner = new Scanner(System.in);
        
        // Create a separate thread for waiting for user input
        Thread inputThread = new Thread(() -> {
            scanner.nextLine();
        });
        
        // Start the thread
        inputThread.start();
        
        // Wait for the thread to complete within the specified time
        try {
            inputThread.join(timeoutSeconds * 1000);
            if (inputThread.isAlive()) {
                // If the thread is still alive, the timeout has occurred
                System.out.println("Manual CAPTCHA input timeout expired.");
                inputThread.interrupt();
                return false;
            }
            return true;
        } catch (InterruptedException e) {
            System.out.println("Manual CAPTCHA input wait was interrupted: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Clicks the Sign Up button to complete registration
     */
    public void clickSignUpButton() {
        scrollAndClick(signUpButton);
    }
    
    /**
     * Checks if email validation error message is present on the page
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isEmailValidationErrorPresent() {
        try {
            // Wait a bit for the error to appear
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Try exact text match first
            if (isElementDisplayed(emailValidationError)) {
                System.out.println("Found email validation error with exact text match");
                return true;
            }
            
            // Try contains text match
            if (isElementDisplayed(emailValidationErrorContains)) {
                System.out.println("Found email validation error with partial text match");
                return true;
            }
            
            // Try by class
            if (isElementDisplayed(emailValidationErrorByClass)) {
                String errorText = driver.findElement(emailValidationErrorByClass).getText().trim();
                System.out.println("Found error message by class with text: " + errorText);
                return errorText.contains("Email not valid");
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Failed to check email validation error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Performs the complete registration process
     * @param email Email for registration
     * @param password Password for registration
     * @return true if registration was successful, false if CAPTCHA was encountered or other issues
     */
    public boolean registerUser(String email, String password) {
        if (!verifyPage()) {
            System.out.println("Not on the registration page");
            return false;
        }
        
        // Fill in the registration form
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        checkAgreeCheckbox();
        
        // Check for CAPTCHA before submitting the form
        if (isCaptchaPresent()) {
            System.out.println("=================================================================");
            System.out.println("CAPTCHA detected! Automated tests CANNOT solve CAPTCHA.");
            System.out.println("=================================================================");
            System.out.println("Options for handling CAPTCHA in test environment:");
            System.out.println("1. Request a test environment without CAPTCHA");
            System.out.println("2. Use a CAPTCHA bypass service (e.g., 2captcha, Anti-Captcha)");
            System.out.println("3. Implement a manual intervention step in the test");
            System.out.println("4. Mock the CAPTCHA verification response in the test environment");
            System.out.println("=================================================================");
            
            if (allowManualCaptchaInput) {
                // Wait for manual CAPTCHA input from the user (maximum 60 seconds)
                boolean captchaHandled = waitForManualCaptchaInput(60);
                if (!captchaHandled) {
                    System.out.println("Failed to wait for manual CAPTCHA input. Test aborted.");
                    return false;
                }
            } else {
                System.out.println("Automated CAPTCHA solving is not possible. Test aborted.");
                return false;
            }
        }
        
        // Submit the registration form
        clickSignUpButton();
        
        return true;
    }
} 