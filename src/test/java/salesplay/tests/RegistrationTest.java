package salesplay.tests;

import com.salesplay.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;

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
        driver.get("https://webpos.salesplaypos.com/registration");
        registrationPage = new RegistrationPage(driver);
        driver.manage().window().maximize();
    }

    @Test (priority = 1)
    @Description("This test attempts to Register into the website without Data.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata1 () throws InterruptedException {

        registrationPage.enterEmail("");
        registrationPage.enterPassword("");
        registrationPage.enterbussinessName("");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Email is required";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

        String expectedmessagepassword = "Password is required";
        String actualValidationMessagePassword = registrationPage.getPasswordValidationMessage();
        assertEquals(actualValidationMessagePassword, expectedmessagepassword);

        String expectedmessagebusinessname = "Business Name is required";
        String actualValidationMessagebusinessname = registrationPage.getBusinessNameValidationMessage();
        assertEquals(actualValidationMessagebusinessname, expectedmessagebusinessname);

    }

    @Test (priority = 2)
    @Description("This test attempts to Register into the website without Data.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata2 () throws InterruptedException {

        registrationPage.enterEmail("");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Email is required";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

    }

    @Test (priority = 3)
    @Description("This test attempts to Register into the website without Data.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata3 () throws InterruptedException {

        registrationPage.enterEmail("savindu@gmail.com");
        registrationPage.enterPassword("");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessagepassword = "Password is required";
        String actualValidationMessagePassword = registrationPage.getPasswordValidationMessage();
        assertEquals(actualValidationMessagePassword, expectedmessagepassword);

    }

    @Test (priority = 4)
    @Description("This test attempts to Register into the website without Data.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata4 () throws InterruptedException {

        registrationPage.enterEmail("savindu@gmail.com ");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessagebusinessname = "Business Name is required";
        String actualValidationMessagebusinessname = registrationPage.getBusinessNameValidationMessage();
        assertEquals(actualValidationMessagebusinessname, expectedmessagebusinessname);

    }

    @Test (priority = 5)
    @Description("This test attempts to Register into the website without Data (Add Spaces for Each field).")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata5 () throws InterruptedException {

        registrationPage.enterEmail("     ");
        registrationPage.enterPassword("     ");
        registrationPage.enterbussinessName("     ");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Email is required";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

        String expectedmessagepassword = "Password is required";
        String actualValidationMessagePassword = registrationPage.getPasswordValidationMessage();
        assertEquals(actualValidationMessagePassword, expectedmessagepassword);

        String expectedmessagebusinessname = "Business Name is required";
        String actualValidationMessagebusinessname = registrationPage.getBusinessNameValidationMessage();
        assertEquals(actualValidationMessagebusinessname, expectedmessagebusinessname);

    }

    @Test (priority = 6)
    @Description("This test attempts to Register into the website without Data (Add Spaces for Each field).")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata6 () throws InterruptedException {

        registrationPage.enterEmail("     ");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Email is required";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

    }

    @Test (priority = 7)
    @Description("This test attempts to Register into the website without Data (Add Spaces for Each field).")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata7 () throws InterruptedException {

        registrationPage.enterEmail("savindu@gmail.com");
        registrationPage.enterPassword("     ");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessagepassword = "Password is required";
        String actualValidationMessagePassword = registrationPage.getPasswordValidationMessage();
        assertEquals(actualValidationMessagePassword, expectedmessagepassword);

    }

    @Test (priority = 8)
    @Description("This test attempts to Register into the website without Data (Add Spaces for Each field).")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata8 () throws InterruptedException {

        registrationPage.enterEmail("savindu@gmail.com");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("     ");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessagebusinessname = "Business Name is required";
        String actualValidationMessagebusinessname = registrationPage.getBusinessNameValidationMessage();
        assertEquals(actualValidationMessagebusinessname, expectedmessagebusinessname);

    }

    @Test (priority = 9)
    @Description("This test attempts to Register into the website without Valid Data .")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata9 () throws InterruptedException {

        registrationPage.enterEmail("test.@yopmail.com");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Enter valid email";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

    }

    @Test (priority = 10)
    @Description("This test attempts to Register into the website without Valid Data .")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata10 () throws InterruptedException {


        registrationPage.enterEmail("testyopmail.com");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessageemail = "Enter valid email";
        String actualValidationMessageEmail = registrationPage.getEmailValidationMessage();
        assertEquals(actualValidationMessageEmail, expectedmessageemail);

    }

    @Test (priority = 11)
    @Description("This test attempts to Register into the website without Valid Data .")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata11 () throws InterruptedException {

        registrationPage.enterEmail("test.@yopmail.com");
        registrationPage.enterPassword("Asd123");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(2000);

        String expectedmessagepassword = "Password must be at least 8 characters";
        String actualValidationMessagePassword = registrationPage.getPasswordValidationMessage();
        assertEquals(actualValidationMessagePassword, expectedmessagepassword);

    }

    @Test (priority = 12)
    @Description("This test attempts to Register into the website with Valid Data and verify the Create Product button.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata12 () throws InterruptedException {

        Random rand = new Random();

        int rand_int1 = rand.nextInt(1000);

        registrationPage.enterEmail("test"+rand_int1+"@yopmail.com");
        registrationPage.enterPassword("Asd12345");
        registrationPage.enterbussinessName("Burl Cafe");

        registrationPage.clickSubmitButton();

        Thread.sleep(10000);

        String expectedRegistrationscuccessmessage = "The confirmation email has been sent to test"+rand_int1+"@yopmail.com";

        String actualValidationMessagePassword = registrationPage.getRegistrationsuccestionMessage();
        assertEquals(actualValidationMessagePassword, expectedRegistrationscuccessmessage);

        registrationPage.clickOnEmailConfirmButton();

        Thread.sleep(2000);

        registrationPage.clickOnexeSkipButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button [@class ='btn btn-primary']")));

        registrationPage.clickOnCreateProductbutton();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));

        String newTabURL = driver.getCurrentUrl();

        // Define the expected URL
        String expectedUrl = "https://cloud.salesplaypos.com/products";

        assertEquals(newTabURL, expectedUrl);

    }

    @AfterMethod
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
