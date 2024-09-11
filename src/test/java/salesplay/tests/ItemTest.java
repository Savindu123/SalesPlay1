package salesplay.tests;

import com.salesplay.ItemPage;
import com.salesplay.LoginPage;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //private static final Logger log = LoggerFactory.getLogger(ItemTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private ItemPage itemPage;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_setting_values.notifications", 1);

        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
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

        Thread.sleep(2000);

        System.out.println("debug - "+ loginPage.getItemCount());

        int actualcountOfItems = loginPage.getItemCount();

        int expectedcountOfitems = 2;

        assertEquals(actualcountOfItems, expectedcountOfitems);
    }

    @Test
    public void testItemAddToCart()throws InterruptedException {

        itemPage.clickFirstItem();

        Thread.sleep(3000);

    }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
