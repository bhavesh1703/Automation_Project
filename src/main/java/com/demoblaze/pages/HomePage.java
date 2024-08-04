package com.demoblaze.pages;

import com.demoblaze.modals.SignUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(id = "signin2")
    WebElement NavSignIn;

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver); //Call the  constructor of BasePage class to access the functions.
    }

    public String hoverOnSignUp() {
        waitForElementToBeVisible(NavSignIn);
        commonActions.hoverOnElement(driver, NavSignIn);
        return commonActions.getHoverColor(driver, NavSignIn);
    }
    public SignUp clickNavSignUp() {
       waitForElementToBeVisible(NavSignIn);
       commonActions.hoverOnElement(driver, NavSignIn);             //Use the method from BasePage.
       commonActions.clickLink(driver,NavSignIn);
       return new SignUp(driver);
    }

}
