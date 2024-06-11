package salesplay.tests;

import com.salesplay.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
//        String baseUrl = "https://webpos.salesplaypos.com/sign_in";
//        driver = WebDriverManager.chromedriver().create();
//        System.out.println("Chrome is Starting");
//        driver.get(baseUrl);
        WebDriverManager.chromedriver().setup();
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
        public void testLogin(String email, String password, String expectedmessage) throws InterruptedException {

            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            Thread.sleep(1000);

            String actualValidationMessage = loginPage.getValidationMessage();

            assertEquals(actualValidationMessage, expectedmessage);

        }
        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
