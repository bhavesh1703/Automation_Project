package com.demoblaze.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CommonActions {
    protected WebDriver driver;
    protected final Actions actions;
    private final Logger logger = LoggerFactory.getLogger(CommonActions.class);
    protected Alert alert;


    public CommonActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    //Generic method to click on Link
    public void clickLink(WebDriver driver, WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        try {
            ele.click();
            logger.info("Clicked on element: " + ele);
        } catch (StaleElementReferenceException e) {
            logger.error("Element is stale: " + e.getMessage());
        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + e.getMessage());
        }
    }

    // Generic method to enter text
    public void enterText(WebDriver driver, WebElement ele, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ele));
        ele.clear();
        ele.sendKeys(text);
        logger.info("Entered text: " + text + " into element: " + ele);
    }

    public boolean isElementPresent(WebDriver driver, WebElement ele) {
       try {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           wait.until(ExpectedConditions.visibilityOf(ele));
           return true;
       }    catch (Exception e) {
           return false;
       }
    }

    public void hoverOnElement(WebDriver driver, WebElement ele) {
        actions.moveToElement(ele).perform();
    }

    public String getHoverColor(WebDriver driver, WebElement ele) {
        hoverOnElement(driver, ele);
        return ele.getCssValue("color");
    }

}
