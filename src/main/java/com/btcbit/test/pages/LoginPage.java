package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final By loginEmailField = By.cssSelector("input[type='email']");
    private final By loginPasswordField = By.cssSelector("input[type='password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginErrorMessage = By.cssSelector("div.bg-error.fs-16-inter-medium");
    
    private final String signInUrl = "https://btcbit.net/sign_in/";
    private final String expectedLoginErrorText = "Invalid email or password. Try clicking 'Forgot Password' if you're having trouble signing in.";
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Verifies that we are on the login page
     * @return true if we are on the correct page
     */
    public boolean verifyPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(signInUrl));
            return driver.getCurrentUrl().equals(signInUrl);
        } catch (Exception e) {
            System.out.println("Failed to verify login page URL: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Enters email in the login field
     * @param email Email for login
     */
    public void enterEmail(String email) {
        sendKeys(loginEmailField, email);
    }
    
    /**
     * Enters password in the login field
     * @param password Password for login
     */
    public void enterPassword(String password) {
        sendKeys(loginPasswordField, password);
    }
    
    /**
     * Clicks the Login button
     */
    public void clickLoginButton() {
        scrollAndClick(loginButton);
    }
    
    /**
     * Checks if login error message is present and contains expected text
     * @return true if error message is displayed with correct text
     */
    public boolean isLoginErrorPresent() {
        try {
            // Увеличиваем время ожидания до 5 секунд
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            boolean isDisplayed = isElementDisplayed(loginErrorMessage);
            if (!isDisplayed) {
                return false;
            }
            
            String actualText = driver.findElement(loginErrorMessage).getText().trim();
            boolean hasCorrectText = actualText.equals(expectedLoginErrorText);
            
            if (!hasCorrectText) {
                System.out.println("Error message text mismatch.");
                System.out.println("Expected: " + expectedLoginErrorText);
                System.out.println("Actual: " + actualText);
            }
            
            return hasCorrectText;
        } catch (Exception e) {
            System.out.println("Failed to check login error message: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Performs the complete login process
     * @param email Email for login
     * @param password Password for login
     * @return true if login was successful, false otherwise
     */
    public boolean loginUser(String email, String password) {
        if (!verifyPage()) {
            System.out.println("Not on the login page");
            return false;
        }
        
        // Fill in the login form
        enterEmail(email);
        enterPassword(password);
        
        // Submit the login form
        clickLoginButton();
        
        // Wait a bit for error message or redirect
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Check if login failed (error message present)
        return !isLoginErrorPresent();
    }
} 