package com.demoblaze.tests;

import com.demoblaze.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected WebDriverWait wait;
    private Properties properties;

    @BeforeClass
    public void setup() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/resources/config.properties")) {
            // Using try-with-resources to ensure the FileInputStream is closed
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties file", e);
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));   // Default timeout of 10 sec.
        driver.get(properties.getProperty("URL2"));
        driver.manage().window().maximize();
        goHome();
    }

    public void goHome() {
       homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                Thread.sleep(5000);
                driver.quit();
            } catch (Exception e) {
                // Ignore exceptions during quit, as we're already in a cleanup phase
            }
        }
    }
}