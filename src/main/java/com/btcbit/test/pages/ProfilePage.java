package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    // Profile page elements
    private final By personalInfoHeader = By.cssSelector("h3.fs-22-manrope-semibold");
    private final By profileEmail = By.cssSelector("span.fs-18-manrope-semibold.mt-3.flex.items-center.text-black");
    
    private final String profileUrl = "https://btcbit.net/profile/";
    
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Verifies that we are on the profile page
     * @return true if we are on the correct page
     */
    public boolean verifyPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(profileUrl));
            return driver.getCurrentUrl().equals(profileUrl);
        } catch (Exception e) {
            System.out.println("Failed to verify profile page URL: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Waits for profile page to load after registration or login
     */
    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.urlToBe(profileUrl));
        } catch (Exception e) {
            System.out.println("Failed to wait for profile page load: " + e.getMessage());
        }
    }
    
    /**
     * Checks if Personal Information section is visible
     * @return true if the section header is displayed
     */
    public boolean isPersonalInformationSectionVisible() {
        try {
            boolean isDisplayed = isElementDisplayed(personalInfoHeader);
            String headerText = driver.findElement(personalInfoHeader).getText().trim();
            boolean hasCorrectText = headerText.equals("Personal information");
            
            if (!hasCorrectText) {
                System.out.println("Personal information header text mismatch.");
                System.out.println("Expected: Personal information");
                System.out.println("Actual: " + headerText);
            }
            
            return isDisplayed && hasCorrectText;
        } catch (Exception e) {
            System.out.println("Failed to check Personal Information section: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Gets the email displayed in the profile
     * @return the displayed email or null if not found
     */
    public String getDisplayedProfileEmail() {
        try {
            return driver.findElement(profileEmail).getText().trim();
        } catch (Exception e) {
            System.out.println("Failed to get profile email: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Verifies that the displayed email matches the expected one
     * @param expectedEmail the email to check against
     * @return true if the emails match
     */
    public boolean verifyProfileEmail(String expectedEmail) {
        String actualEmail = getDisplayedProfileEmail();
        if (actualEmail == null) {
            return false;
        }
        
        boolean emailsMatch = actualEmail.equals(expectedEmail);
        if (!emailsMatch) {
            System.out.println("Profile email mismatch.");
            System.out.println("Expected: " + expectedEmail);
            System.out.println("Actual: " + actualEmail);
        }
        
        return emailsMatch;
    }
} 