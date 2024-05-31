package com.salesplay.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        // Set up ChromeDriver
        String baseUrl = "https://webpos.salesplaypos.com/sign_in";
        WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Chrome is Starting");
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() throws IOException {
        List<Object[]> loginData = new ArrayList<>();
        Reader in = new FileReader("src/main/resources/LoginTestdata.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            loginData.add(new Object[]{record.get("email"), record.get("password")});

        }
        return loginData.iterator();
    }

        @Test(dataProvider = "loginData")
        public void testLogin(String email, String password) {
            WebElement emailField = driver.findElement(By.id("replace_password"));
            emailField.sendKeys(email);

            WebElement passwordField = driver.findElement(By.id("replace_password"));
            passwordField.sendKeys(password);

            WebElement loginButton = driver.findElement(By.id("sign_in_link"));
            loginButton.click();

            WebElement validationMessageElement = driver.findElement(By.xpath("//label[@class='err_msg']"));
            String actualValidationMessage = validationMessageElement.getText();
            String expectedValidationMessage = "Incorrect Username or Password "; // Adjust as needed

            Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
        }
        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
