package com.demoblaze.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.demoblaze.utils.CommonActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected CommonActions commonActions;
    private final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected Actions actions;
    protected Alert alert;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.commonActions = new CommonActions(driver);
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for an element to be visible on the page.
     * @param ele The WebElement to wait for.
     */
    public void waitForElementToBeVisible(WebElement ele) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(ele));
            logger.info("Element is now visible: " + ele);
        } catch (TimeoutException e) {
            logger.error("Element not visible after waiting: " + ele, e);
        }
    }

    public String getAlertMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            String alertmessage = alert.getText();
            alert.accept();
            return alertmessage;
        } catch (UnhandledAlertException e) {
            logger.error("Unhandled alert present", e);
            throw e;
        } catch (Exception e) {
            logger.error("No Alert present", e);
            throw new RuntimeException("No alert present");
        }
    }

    //Other generic methods here

}
