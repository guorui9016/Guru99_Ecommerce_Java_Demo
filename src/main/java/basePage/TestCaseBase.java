package basePage;

import helper.ConfigHelper;
import helper.InitDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class TestCaseBase {

    protected WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();

    protected WebDriver initWebDriver(String browser){
        InitDriverHelper initDriverHelper = new InitDriverHelper();
        WebDriver driver = initDriverHelper.createDriver(browser, Boolean.parseBoolean(ConfigHelper.getValue("headless")));
        return driver;
    };

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser){
        driver = initWebDriver(browser);
        driver.get(ConfigHelper.getValue("url"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
