package salesplay.tests;

import com.salesplay.ItemPage;
import com.salesplay.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class Included_Tax_Item_Test {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {


        // Set up the ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Create a Map to store Chrome preferences
        Map<String, Object> prefs = new HashMap<>();

        // Set Chrome to allow notifications by default
        // 1 means 'allow', 2 would mean 'block', and 0 would mean 'ask'
        prefs.put("profile.default_content_setting_values.notifications", 1);

        // Add the preferences to Chrome options
        options.setExperimentalOption("prefs", prefs);

        // Initialize the ChromeDriver with the configured options
        driver = new ChromeDriver(options);

        driver.get("https://webpos.salesplaypos.com/sign_in");
        loginPage = new LoginPage(driver);

        //Maximize the Browser
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testItemCount () throws InterruptedException {

        //calling methods from LoginPage.java
        loginPage.enterEmail("Liveauto@testmail.com");
        loginPage.enterPassword("Asd12345");
        loginPage.clickLoginButton();

        //Wait the Process
        Thread.sleep(2000);

        //calling methods from LoginPage.java
        loginPage.clickContinueButton();

        //Wait the Process
        Thread.sleep(1000);

        //Wait the Process
        loginPage.clickLoginButton2();

        //Wait the Process till the element is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='intro-y block col-span-6 sm:col-span-4 2xl:col-span-3 productinfo ']")));
    }




    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
