package salesplay.tests;

import com.salesplay.LoginPage;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() throws IOException {
        List<Object[]> loginData = new ArrayList<>();
        Reader in = new FileReader("src/main/resources/LoginTestdata.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            loginData.add(new Object[]{record.get("email"), record.get("password"), record.get("expectedmessage")});
        }
        return loginData.iterator();
    }

        @Test(dataProvider = "loginData")
        @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
        @Severity(CRITICAL)
        @Link(name = "Website", url = "https://webpos.salesplaypos.com/sign_in")
        public void testLogin(String email, String password, String expectedmessage) throws InterruptedException {

            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            Thread.sleep(2000);
            String actualValidationMessage = loginPage.getValidationMessage();
            assertEquals(actualValidationMessage, expectedmessage);
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