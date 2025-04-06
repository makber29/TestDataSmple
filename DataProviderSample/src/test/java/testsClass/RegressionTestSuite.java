package testsClass;

import data.TestDataProvider;
import data.TestDataWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
public class RegressionTestSuite {

    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");    }

    @Test
    public void verifyCreateAnAccountPage(TestDataWrapper testDataWrapper) {
        String createAccountButton = (String) testDataWrapper.getInputValueByKey("createAccountButton");
        System.out.println(createAccountButton + "  Regression Test Suite");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
