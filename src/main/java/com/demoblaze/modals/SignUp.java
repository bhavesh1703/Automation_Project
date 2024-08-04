package com.demoblaze.modals;

import com.demoblaze.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp extends BasePage {


    public SignUp(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "(//div[@class='modal-content'])[2]")
    WebElement modelbox;

    @FindBy(xpath = "//h5[@id='signInModalLabel']")
    WebElement signupTitleText;

    @FindBy(id = "sign-username")
    WebElement signupUsername;

    @FindBy(id = "sign-password")
    WebElement signupPassword;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;

    @FindBy(xpath = "(//button[text()='Close'])[2]")
    WebElement closeButton;

    @FindBy(xpath = "(//button[@class='close'])[2]")
    WebElement closeIcon;

    // Methods to perform actions
    public void setSignupUsername(String username) {
        if (isModalPresent()) {
            if (signupUsername != null) {
                commonActions.enterText(driver, signupUsername, username);
            } else {
                throw new IllegalStateException("Sign up modal is not present");
            }
        }
    }

    public void setSignupPassword(String password) {
        if  (isModalPresent()) {
            if (signupPassword != null) {
                commonActions.enterText(driver, signupPassword, password);
            }   else {
                throw new IllegalStateException("Sign up modal is not present");
            }
        }
    }

    public void clickOnSignupButton() {
        commonActions.clickLink(driver, signUpButton);
    }

    public void clickOnCloseButton() {
        commonActions.hoverOnElement(driver, closeIcon);
        commonActions.clickLink(driver, closeButton);
    }

    public void clickOnCloseIcon() {
        if (isCloseIconPresent()) {
            commonActions.hoverOnElement(driver, closeIcon);
            commonActions.clickLink(driver, closeIcon);
        } else {
            throw new IllegalStateException("Close icon is not present");
        }
    }

    public String getSignupModelTitle() {
        if(isModalPresent()) {
            return signupTitleText.getText();
        }else {
            throw new IllegalStateException("Sign up Title is not present");
        }
    }

    private boolean isModalPresent() {
        return commonActions.isElementPresent(driver, modelbox );
    }

    private boolean isCloseIconPresent() {
        return commonActions.isElementPresent(driver, closeIcon );
    }

    public String hoverOnCloseButton() {
        waitForElementToBeVisible(closeButton);
        commonActions.hoverOnElement(driver, closeButton);
        return commonActions.getHoverColor(driver, closeButton);
    }

    public String hoverOnSignupButton() {
        waitForElementToBeVisible(signUpButton);
        commonActions.hoverOnElement(driver, signUpButton);
        return commonActions.getHoverColor(driver, signUpButton);
    }

    public String hoverOnCloseIcon() {
        waitForElementToBeVisible(closeIcon);
        commonActions.hoverOnElement(driver, closeIcon);
        return commonActions.getHoverColor(driver, closeIcon);
    }
}