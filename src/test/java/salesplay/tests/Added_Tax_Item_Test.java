package salesplay.tests;

import com.salesplay.ItemPage;
import com.salesplay.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class Added_Tax_Item_Test {

    //private static final Logger log = LoggerFactory.getLogger(ItemTest.class);
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

        //Wait the Process
        Thread.sleep(2000);

        //calling methods from LoginPage.java
        System.out.println("Item Count  - "+ loginPage.getItemCount());

        //Assign to variable
        int actualcountOfItems = loginPage.getItemCount();

        //Expected count of items
        int expectedcountOfitems = 4;

        assertEquals(actualcountOfItems, expectedcountOfitems);
    }

    @Test(priority = 2)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testCategoryCount () throws InterruptedException {

        //calling methods from LoginPage.java
        System.out.println("Item Count  - "+ loginPage.getCategoryCount());

        //Assign to variable
        int actualcountOfCategories = loginPage.getCategoryCount();

        //Expected count of items
        int expectedcountOfCategoriess = 2;

        //Validate with actualcountOfCategories expectedcountOfCategoriess
        assertEquals(actualcountOfCategories, expectedcountOfCategoriess);

    }

    @Test(priority = 3)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void enablePrinterSetup() throws InterruptedException {

        //Calling methods from LoginPage.java
        loginPage.clickSettingButton();
        loginPage.clickprinterSetupButton();
        loginPage.clickaddPrinterButton();
        loginPage.clickaddNewButton();

        //Wait the Process
        Thread.sleep(2000);

        //Calling methods from LoginPage.java
        loginPage.enterPrinterName("Test");
        loginPage.clickprintReceiptsToggleButton();
        loginPage.clickConnectionTypeDropdown();

        // Find the dropdown element for selecting the connection mode
        WebElement dropdownElement = driver.findElement(By.id("select_connection_mode"));

        // Create a Select object to interact with the dropdown
        Select dropdown = new Select(dropdownElement);

        // Select the "Wifi" option from the dropdown
        dropdown.selectByVisibleText("Wifi");

        // Enter the printer's WiFi IP address (e.g., 10.30.100.99)
        loginPage.enterPrinterWifi("10.30.100.99");

        // Click the "Save" button to save the WiFi settings
        loginPage.clickSaveButton();

        // Navigate to the home page
        loginPage.clickHomeButton();

    }

//    @Test(priority = 4)
//    public void runAllTestsMultipleTimes() throws InterruptedException {
//
//        // Execute a suite of tests multiple times
//        for (int i = 0; i < 2; i++) {  // Repeat all tests 3 times
//            System.out.println("Test execution iteration: " + (i + 1));
//
//            // Call all test methods within the loop
//            testItemAddToCart();
//            testGrandtotalWith1AddedTax();
//            testSystemNavigatesToPaymentPage();
//            testPaymentPageChargeButton();
//            testPastReceipt();
//        }
//    }

    @Test(priority = 4)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testItemAddToCart()throws InterruptedException {

        Thread.sleep(200);

        //calling methods from LoginPage.java
        loginPage.clickFirstItem();

        //wait the process
        Thread.sleep(500);

        //
        WebElement cartElement = driver.findElement(By.xpath("//a[@class='flex items-center p-3 cursor-pointer transition duration-300 ease-in-out bg-white dark:bg-darkmode-600 hover:bg-slate-100 dark:hover:bg-darkmode-400 rounded-md billing_items']"));

        // Check if the element is displayed
        boolean isDisplayed = cartElement.isDisplayed();

        // Print the result and add an assertion
        Assert.assertTrue(isDisplayed, "The product is not visible in the cart.");

        //Thread.sleep(1000);
    }

    @Test(priority = 5)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testGrandtotalWith1AddedTax() {

        String itemAmount = loginPage.getItemAmount1st();

        // Parse and validate the item amount
        double ItemAmount = 0;
        try {
            // Attempt to convert the string itemAmount to a double
            ItemAmount = Double.parseDouble(itemAmount);

            // Handle the case where itemAmount is not a valid number
            System.out.println("Item Amount: " + ItemAmount);
        } catch (NumberFormatException e) {

            System.out.println("Invalid number format: " + e.getMessage());
        }
        //the tax is 2.5%
        double tax = 0.025;

        // Calculate the tax amount
        double taxAmount = ItemAmount * tax;

        // Calculate the total amount (item price + tax)
        double totalAmount = ItemAmount + taxAmount;

        // Retrieve the expected grand total from the login page
        double expectedAmount = Double.parseDouble(loginPage.getGrandTotal());

        // Assert that the calculated total matches the expected amount
        Assert.assertEquals(totalAmount, expectedAmount, "Total amount is incorrect");

        // Log the total amount for debugging or verification purposes
        System.out.println("Total amount: " + totalAmount);


    }

    @Test(priority = 6)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testSystemNavigatesToPaymentPage() throws InterruptedException {

        // Verify navigation to the Payment page
        loginPage.clickItemPageChargeButton();

        // Wait for the Payment page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the text of the payment element
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lang_payment")));

        // Brief pause (Note: Consider replacing with a more robust wait strategy)
        String actualText = element.getText();

        Thread.sleep(500);

        // Verify the Payment page is displayed correctly
        String expectedText = "Payment";
        Assert.assertEquals(actualText, expectedText, "Payment page is not displayed");
    }

    @Test(priority = 7)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testPaymentPageChargeButton() throws InterruptedException {

        loginPage.clickFinalChargeButton();

        Thread.sleep(1000);

        //loginPage.clickNewSaleButton();
    }

    @Test(priority = 8)
    @Description("Waiting for the Description")
    @Severity(CRITICAL)
    public void testPastReceipt() throws InterruptedException {

        Thread.sleep(2000);

        loginPage.clickPastReceiptButton();

        Thread.sleep(500);

        //Get the value actualReceiptTotal from system
        double actualReceiptTotal = Double.parseDouble( loginPage.getPastreceiptTotal());

        System.out.println("Actual Receipt Total: "+actualReceiptTotal);

        //need to get the value from system ****************************
        double expectedReceiptTotal = 205.00;

        System.out.println("Expected Receipt Total: "+ expectedReceiptTotal);

        //Validate the actualReceiptTotal and expectedvalue
        Assert.assertEquals(actualReceiptTotal, expectedReceiptTotal, "Total amount is incorrect");

        //******************** should test Past receipt other elements *******************//

    }

    @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

    @AfterMethod (alwaysRun = true)
    public void takeScreenshot(ITestResult result){
        if(!result.isSuccess()){
            //Take screenshot via webDriver
            File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Save screenshot and convert to inputStream
            try {
                FileUtils.copyFile(screenShot, new File("build/screenshots/failedTestScreenshot.png"));
                InputStream is = Files.newInputStream(Paths.get("build/screenshots/failedTestScreenshot.png"));
                //Attach screenshot to allure reports
                Allure.addAttachment("Last system snapshot",is);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    }