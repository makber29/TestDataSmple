package testsClass;

import data.TestDataProvider;
import data.TestDataWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
public class SmokeTestSuite {

    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");    }

    @Test(priority = 0)
    public void verifyHomePage(TestDataWrapper testDataWrapper) {
        String expectedPageTitle = (String) testDataWrapper.getInputValueByKey("expectedPageTitle");
        String logo = (String) testDataWrapper.getInputValueByKey("logo");
        String siginInLink = (String) testDataWrapper.getInputValueByKey("siginInLink");
        System.out.println(expectedPageTitle + "  Smoke Test Suite");
        System.out.println(logo + "  Smoke Test Suite");
        System.out.println(siginInLink + "  Smoke Test Suite");


    }

    @Test(priority = 1)
    public void verifyDashboardMenu(TestDataWrapper testDataWrapper) {
        String expectedMenu = (String) testDataWrapper.getInputValueByKey("expectedMenu");
        String userLink = (String) testDataWrapper.getInputValueByKey("userLink");
        System.out.println(expectedMenu + "  Smoke Test Suite");
        System.out.println(userLink + "  Smoke Test Suite");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
