import basePage.TestCaseBase;
import helper.ConfigHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageRepository.HomePage;
import pageRepository.MobilePage;

import java.util.Arrays;

public class TestCase1 extends TestCaseBase {
    WebDriver driver;
    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser){
        driver = initWebDriver(browser);
        driver.get(ConfigHelper.getValue("url"));
    }

    @Test
    public void testCase01(){
        //step 1: check the title of the home page
        HomePage homePage = new HomePage(driver);
        String titleText = homePage.getPageTitleText().trim();
        Assert.assertEquals(titleText, "THIS IS DEMO SITE FOR");

        //step 2: goto mobile page and check the title
        MobilePage mobilePage = homePage.clickMobileLink();
        Assert.assertEquals(mobilePage.getPageTitle(),"MOBILE");

        //step 3: sort the products by name and check the name
        String[] allProductsName = mobilePage.getAllProductsName();
        Arrays.sort(allProductsName);
        mobilePage.setSortBySelect("Name");
        Assert.assertEquals(mobilePage.getAllProductsName(), allProductsName);
    }
}
