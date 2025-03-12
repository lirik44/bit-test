package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactsPage extends BasePage {
    private final String contactsUrl = "https://btcbit.net/contacts/";
    
    // Company information locators
    private final By polandOfficeHeader = By.xpath("//h2[text()='Poland']");
    private final By estoniaOfficeHeader = By.xpath("//h2[text()='Estonia']");
    
    private final By polandCompanyName = By.xpath("//h3[contains(text(), 'BTCBIT Sp. z o.o.')]");
    private final By polandAddressCompany = By.xpath("//h3[contains(.,'BTCBIT Sp. z o.o. ')]");
    private final By polandAddressStreet = By.xpath("//h3[contains(.,'Ul. Gesia 8 - 205, 31-535 ')]");
    private final By polandAddressCity = By.xpath("//h3[contains(.,'Krakow, Poland')]");
    private final By polandRegNumber = By.xpath("//div//p[contains(text(), '369827363')]");
    private final By polandPhone = By.xpath("//li/a[@href='tel:+48588813222']");
    
    private final By estoniaCompanyName = By.xpath("//h3[contains(text(), 'BTCBIT OÜ')]");
    private final By estoniaAddressCompany = By.xpath("//h3[contains(.,'BTCBIT OÜ ')]");
    private final By estoniaAddressStreet = By.xpath("//h3[contains(.,'Pikk tn 33-3, 10133')]");
    private final By estoniaAddressCity = By.xpath("//h3[contains(.,'Tallinn, Estonia')]");
    private final By estoniaRegNumber = By.xpath("//div//p[contains(text(), '16121208')]");
    private final By estoniaPhone = By.xpath("//li/a[@href='tel:+3728803222']");
    
    public ContactsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Verifies that we are on the contacts page
     * @return true if we are on the correct page
     */
    public boolean verifyPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(contactsUrl));
            return driver.getCurrentUrl().equals(contactsUrl);
        } catch (Exception e) {
            System.out.println("Failed to verify contacts page URL: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Verifies that Poland office information is displayed
     * @return true if all Poland office information is present
     */
    public boolean verifyPolandOfficeInfo() {
        // Scroll to Poland section first
        try {
            System.out.println("Scrolling to Poland office section...");
            scrollToElement(driver.findElement(polandOfficeHeader));
            
            // Wait a bit for any animations to complete
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll to Poland office section: " + e.getMessage());
            return false;
        }

        System.out.println("Checking Poland office header...");
        boolean headerDisplayed = isElementDisplayed(polandOfficeHeader);
        if (!headerDisplayed) {
            System.out.println("Poland office header is not displayed");
            return false;
        }

        System.out.println("Checking Poland company name...");
        boolean companyDisplayed = isElementDisplayed(polandCompanyName);
        if (!companyDisplayed) {
            System.out.println("Poland company name is not displayed");
            return false;
        }

        System.out.println("Checking Poland address company line...");
        boolean addressCompanyDisplayed = isElementDisplayed(polandAddressCompany);
        if (!addressCompanyDisplayed) {
            System.out.println("Poland address company line is not displayed");
            return false;
        }

        System.out.println("Checking Poland address street line...");
        boolean addressStreetDisplayed = isElementDisplayed(polandAddressStreet);
        if (!addressStreetDisplayed) {
            System.out.println("Poland address street line is not displayed");
            return false;
        }

        System.out.println("Checking Poland address city line...");
        boolean addressCityDisplayed = isElementDisplayed(polandAddressCity);
        if (!addressCityDisplayed) {
            System.out.println("Poland address city line is not displayed");
            return false;
        }

        System.out.println("Checking Poland registration number...");
        boolean regDisplayed = isElementDisplayed(polandRegNumber);
        if (!regDisplayed) {
            System.out.println("Poland registration number is not displayed");
            return false;
        }

        System.out.println("Checking Poland phone number...");
        boolean phoneDisplayed = isElementDisplayed(polandPhone);
        if (!phoneDisplayed) {
            System.out.println("Poland phone number is not displayed");
            return false;
        }
        
        // Verify registration number
        System.out.println("Verifying Poland registration number text...");
        String regNumber = driver.findElement(polandRegNumber).getText().trim();
        System.out.println("Found Poland reg number: " + regNumber);
        if (!regNumber.contains("369827363")) {
            System.out.println("Poland office registration number mismatch");
            return false;
        }
        
        // Verify phone number
        System.out.println("Verifying Poland phone number text...");
        String phone = driver.findElement(polandPhone).getText().trim();
        System.out.println("Found Poland phone: " + phone);
        if (!phone.contains("+48 588 813 222")) {
            System.out.println("Poland office phone number mismatch");
            return false;
        }
        
        System.out.println("All Poland office information verified successfully");
        return true;
    }
    
    /**
     * Verifies that Estonia office information is displayed
     * @return true if all Estonia office information is present
     */
    public boolean verifyEstoniaOfficeInfo() {
        // Scroll to Estonia section first
        try {
            System.out.println("Scrolling to Estonia office section...");
            scrollToElement(driver.findElement(estoniaOfficeHeader));
            
            // Wait a bit for any animations to complete
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll to Estonia office section: " + e.getMessage());
            return false;
        }

        System.out.println("Checking Estonia office header...");
        boolean headerDisplayed = isElementDisplayed(estoniaOfficeHeader);
        if (!headerDisplayed) {
            System.out.println("Estonia office header is not displayed");
            return false;
        }

        System.out.println("Checking Estonia company name...");
        boolean companyDisplayed = isElementDisplayed(estoniaCompanyName);
        if (!companyDisplayed) {
            System.out.println("Estonia company name is not displayed");
            return false;
        }

        System.out.println("Checking Estonia address company line...");
        boolean addressCompanyDisplayed = isElementDisplayed(estoniaAddressCompany);
        if (!addressCompanyDisplayed) {
            System.out.println("Estonia address company line is not displayed");
            return false;
        }

        System.out.println("Checking Estonia address street line...");
        boolean addressStreetDisplayed = isElementDisplayed(estoniaAddressStreet);
        if (!addressStreetDisplayed) {
            System.out.println("Estonia address street line is not displayed");
            return false;
        }

        System.out.println("Checking Estonia address city line...");
        boolean addressCityDisplayed = isElementDisplayed(estoniaAddressCity);
        if (!addressCityDisplayed) {
            System.out.println("Estonia address city line is not displayed");
            return false;
        }

        System.out.println("Checking Estonia registration number...");
        boolean regDisplayed = isElementDisplayed(estoniaRegNumber);
        if (!regDisplayed) {
            System.out.println("Estonia registration number is not displayed");
            return false;
        }

        System.out.println("Checking Estonia phone number...");
        boolean phoneDisplayed = isElementDisplayed(estoniaPhone);
        if (!phoneDisplayed) {
            System.out.println("Estonia phone number is not displayed");
            return false;
        }
        
        // Verify registration number
        System.out.println("Verifying Estonia registration number text...");
        String regNumber = driver.findElement(estoniaRegNumber).getText().trim();
        System.out.println("Found Estonia reg number: " + regNumber);
        if (!regNumber.contains("16121208")) {
            System.out.println("Estonia office registration number mismatch");
            return false;
        }
        
        // Verify phone number
        System.out.println("Verifying Estonia phone number text...");
        String phone = driver.findElement(estoniaPhone).getText().trim();
        System.out.println("Found Estonia phone: " + phone);
        if (!phone.contains("+372 8 803 222")) {
            System.out.println("Estonia office phone number mismatch");
            return false;
        }
        
        System.out.println("All Estonia office information verified successfully");
        return true;
    }
    
    /**
     * Scrolls to company information section
     */
    public void scrollToCompanyInfo() {
        try {
            // Try to scroll to Poland office header first
            scrollToElement(driver.findElement(polandOfficeHeader));
            
            // Wait a bit for any animations to complete
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println("Failed to scroll to company information: " + e.getMessage());
        }
    }
} 