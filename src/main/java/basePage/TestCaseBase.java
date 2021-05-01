package basePage;

import helper.ConfigHelper;
import helper.InitDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestCaseBase {

    protected WebDriver initWebDriver(String browser){
        InitDriverHelper initDriverHelper = new InitDriverHelper();
        WebDriver driver = initDriverHelper.createDriver(browser, Boolean.parseBoolean(ConfigHelper.getValue("headless")));
        return driver;
    };
}
