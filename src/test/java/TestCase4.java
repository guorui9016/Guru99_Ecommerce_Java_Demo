import basePage.TestCaseBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageRepository.ComparedPage;
import pageRepository.HomePage;
import pageRepository.MobilePage;
import pageRepository.ShoppingCartPage;

import java.util.Set;

public class TestCase4 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase4.class);

    @Test
    public void testCase04(){
        //step 1: Add 2 phone into compare list
        HomePage homePage = new HomePage(driver);
        MobilePage mobilePage = homePage.clickMobileLink();
        WebElement sony_xperia = mobilePage.getProductByName("SONY XPERIA");
        mobilePage.addProductToCompareList(sony_xperia);
        WebElement iphone = mobilePage.getProductByName("Iphone");
        mobilePage.addProductToCompareList(iphone);
        logger.info("Add two products: Sony and iphone");
        //step 2: click the compare button and switch to the popup window
        ComparedPage comparedPage = mobilePage.clickCompareButton();
        String title = comparedPage.getTitle();
        softAssert.assertEquals(title, "COMPARE PRODUCTS");
        //step 3: close the window and check the windows is closed
        comparedPage.closeWindow();
        Set<String> windowHandles = driver.getWindowHandles();
        softAssert.assertEquals(windowHandles.size(), 1);
    }
}
