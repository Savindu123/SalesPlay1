package salesplay.tests;

import com.salesplay.LoginPage;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class ItemTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_setting_values.notifications", 1);

        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://webpos.salesplaypos.com/sign_in");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void testItemCount () throws InterruptedException {

        loginPage.enterEmail("Liveauto@testmail.com");
        loginPage.enterPassword("Asd12345");
        loginPage.clickLoginButton();

        Thread.sleep(2000);

        loginPage.clickContinueButton();

        loginPage.clickLoginButton2();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='intro-y block col-span-6 sm:col-span-4 2xl:col-span-3 productinfo ']")));

        //System.out.println("Debug"+element);



        Thread.sleep(5000);

        //List<WebElement> links = driver.findElements(element);
//
//        // Get the count of elements//
//        int countOfLinks = links.length;
//
//        // Print the count
       // System.out.println(links);

//        int expectedcountOfitems = 2;
//
//        assertEquals(actualcountOfItems, expectedcountOfitems);
//
//        System.out.println("Number of Items on the page: " + actualcountOfItems);
    }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }




    }
