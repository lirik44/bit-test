package com.btcbit.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Scrolls to the element and then clicks it
     * @param locator Element locator
     */
    protected void scrollAndClick(By locator) {
        WebElement element = waitForElementVisible(locator);
        scrollToElement(element);
        element.click();
    }
    
    /**
     * Scrolls to the element using JavaScript
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        
        // Add a small delay to allow the scroll to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Clicks an element using JavaScript
     * Useful for elements that are difficult to click with regular Selenium click
     * @param locator Element locator
     */
    protected void clickWithJavaScript(By locator) {
        WebElement element = waitForElementVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }
    
    protected void sendKeys(By locator, String text) {
        waitForElementVisible(locator).sendKeys(text);
    }
    
    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }
    
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
} 