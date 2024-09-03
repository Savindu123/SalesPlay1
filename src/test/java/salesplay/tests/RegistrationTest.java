package salesplay.tests;

import com.salesplay.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        driver = new ChromeDriver();
        driver.get("https://webpos.salesplaypos.com/registration");
        registrationPage = new RegistrationPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    @Description("This test attempts to Register into the website without Data.")
    @Severity(CRITICAL)
    public void testRegistrationwithoutdata () throws InterruptedException {

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
