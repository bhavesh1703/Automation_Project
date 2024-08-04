package com.demoblaze.tests;

import com.demoblaze.modals.SignUp;
import com.demoblaze.pages.HomePage;
import com.demoblaze.utils.CommonActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignUpTest extends BaseTest {

    @Test
    public void testSuccessSignup() {

        SignUp signUp = homePage.clickNavSignUp();
        Assert.assertEquals(signUp.getSignupModelTitle(), "Sign up", "Modal Title is incorrect");
        signUp.setSignupUsername("Tester");
        signUp.setSignupPassword("Tester@123");
        signUp.clickOnSignupButton();

        //Alert Message confirmation handled here
        String actualAlertMessage = signUp.getAlertMessage();
        Assert.assertEquals(actualAlertMessage, "Sign up successful", "Alert message is not present");
    }

    @Test
    public void testCloseButton() {
        SignUp signUp = homePage.clickNavSignUp();
        signUp.setSignupUsername("Tester");
        signUp.setSignupPassword("Tester@123");
        signUp.clickOnCloseButton();
    }

    @Test
    public void testCloseIcon() {
        SignUp signUp = homePage.clickNavSignUp();
        signUp.clickOnCloseIcon();
    }

    @Test
    public void testHoverAction() {
        Assert.assertEquals(homePage.hoverOnSignUp(),
                "rgba(33, 232, 203, 1)", "Signup Hover color is not matched");
        SignUp signUp = homePage.clickNavSignUp();
        Assert.assertEquals(signUp.hoverOnCloseIcon(),
                "rgba(0, 0, 0, 1)", "Close icon hover color is not matched");
        Assert.assertEquals(signUp.hoverOnCloseButton(),
                "rgba(41, 43, 44, 1)", "Close button hover color is not matched" );
        Assert.assertEquals(signUp.hoverOnSignupButton(),
                "rgba(255, 255, 255, 1)", "Signup button hover color is not matched");
    }

    @Test
    public void testSignedUser() {
        SignUp signUp = homePage.clickNavSignUp();
        signUp.setSignupUsername("Tester");
        signUp.setSignupPassword("Tester@123");
        signUp.clickOnSignupButton();

        //Alert Message confirmation handled here
        String actualAlertMessage = signUp.getAlertMessage();
        Assert.assertEquals(actualAlertMessage, "This user already exist.", "Alert message is not present");
    }

    @Test
    public void testBlankFields() {
        SignUp signUp = homePage.clickNavSignUp();
        signUp.clickOnSignupButton();
        String actualAlertMessage = signUp.getAlertMessage();
        Assert.assertEquals(actualAlertMessage, " Please fill out Username and Password.", "Alert message is not present");

    }
}
