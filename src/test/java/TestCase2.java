import basePage.BasePage;
import basePage.TestCaseBase;
import helper.ConfigHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageRepository.HomePage;
import pageRepository.MobilePage;
import pageRepository.ProductDetailsPage;

import java.util.Arrays;

public class TestCase2 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase2.class);

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser){
        driver = initWebDriver(browser);
        driver.get(ConfigHelper.getValue("url"));
    }

    @Test
    public void testCase02(){
        //step 1: find out the Sony Xperia and get the price
        HomePage homePage = new HomePage(driver);
        MobilePage mobilePage = homePage.clickMobileLink();
        WebElement productSony = mobilePage.getProductSony();
        String price = productSony.findElement(By.className("price")).getText();
        logger.info("the price of the sony phone from the product list page is: " + price);
        //step 2: get the price from the details page
        ProductDetailsPage productDetailsPage = mobilePage.gotoProducts(productSony);
        String detailsPagePrice = productDetailsPage.getPrice().getText();
        logger.info("the price of the sony phone from the products details page is: " + price);
        softAssert.assertEquals(price, detailsPagePrice);
        softAssert.assertAll();
    }
}
