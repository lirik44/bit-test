package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final String baseUrl = "https://btcbit.net";

    // Locators
    private final By getStartedLink = By.cssSelector("a.header_signup__VSWAE[href='/sign_up/']");
    private final By getStartedLinkByText = By.linkText("Get Started");
    private final By getStartedLinkByPartialText = By.partialLinkText("Get Started");
    private final By getStartedLinkByXPath = By.xpath("//a[contains(@class, 'header_signup') or contains(@href, 'sign_up')]");

    private final By loginLink = By.cssSelector("a.header_login__VSWAE[href='/sign_in/']");
    private final By loginLinkByText = By.linkText("Login");
    private final By loginLinkByPartialText = By.partialLinkText("Login");
    private final By loginLinkByXPath = By.xpath("//a[contains(@class, 'header_login') or contains(@href, 'sign_in')]");

    private final By contactUsLink = By.cssSelector("a.questions_button__Bb3zE[href='/contacts/']");
    private final By contactUsLinkByText = By.linkText("Contact us");
    private final By contactUsLinkByPartialText = By.partialLinkText("Contact");
    private final By contactUsLinkByXPath = By.xpath("//a[contains(@class, 'questions_button') or contains(@href, '/contacts/')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl);
    }

    public void clickGetStartedLink() {
        try {
            // Try primary locator first
            wait.until(ExpectedConditions.elementToBeClickable(getStartedLink)).click();
        } catch (Exception e) {
            try {
                // Try by text
                wait.until(ExpectedConditions.elementToBeClickable(getStartedLinkByText)).click();
            } catch (Exception e2) {
                try {
                    // Try by partial text
                    wait.until(ExpectedConditions.elementToBeClickable(getStartedLinkByPartialText)).click();
                } catch (Exception e3) {
                    // Try XPath as last resort
                    wait.until(ExpectedConditions.elementToBeClickable(getStartedLinkByXPath)).click();
                }
            }
        }
        
        // Wait for navigation to complete
        wait.until(ExpectedConditions.urlContains("/sign_up"));
    }

    public void clickLoginLink() {
        try {
            // Try primary locator first
            wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        } catch (Exception e) {
            try {
                // Try by text
                wait.until(ExpectedConditions.elementToBeClickable(loginLinkByText)).click();
            } catch (Exception e2) {
                try {
                    // Try by partial text
                    wait.until(ExpectedConditions.elementToBeClickable(loginLinkByPartialText)).click();
                } catch (Exception e3) {
                    // Try XPath as last resort
                    wait.until(ExpectedConditions.elementToBeClickable(loginLinkByXPath)).click();
                }
            }
        }
        
        // Wait for navigation to complete
        wait.until(ExpectedConditions.urlContains("/sign_in"));
    }

    public void clickContactUsLink() {
        try {
            // Try primary locator first
            scrollAndClick(contactUsLink);
        } catch (Exception e) {
            try {
                // Try by text
                scrollAndClick(contactUsLinkByText);
            } catch (Exception e2) {
                try {
                    // Try by partial text
                    scrollAndClick(contactUsLinkByPartialText);
                } catch (Exception e3) {
                    // Try XPath as last resort
                    scrollAndClick(contactUsLinkByXPath);
                }
            }
        }
        
        // Wait for navigation to complete
        wait.until(ExpectedConditions.urlContains("/contacts"));
    }

    public boolean verifyPage() {
        return driver.getCurrentUrl().equals(baseUrl) || driver.getCurrentUrl().equals(baseUrl + "/");
    }
} 