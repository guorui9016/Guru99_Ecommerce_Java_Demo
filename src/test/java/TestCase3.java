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
import pageRepository.ShoppingCartPage;

public class TestCase3 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase3.class);

    @Test
    public void testCase03(){
        //step 1: Add the Sony product into shopping cart from product list
        HomePage homePage = new HomePage(driver);
        MobilePage mobilePage = homePage.clickMobileLink();
        ShoppingCartPage shoppingCartPage = mobilePage.addProductIntoCart(mobilePage.getProductSony());
        //step 4: add 1000 products to cart
        shoppingCartPage.setProductQty(1000);
        shoppingCartPage.clickBtnUpdate();
        //step 5: check the error message
        softAssert.assertEquals(shoppingCartPage.getErrorMsg(), "Some of the products cannot be ordered in requested quantity.");
        softAssert.assertEquals(shoppingCartPage.getErrorMsgDetails(), "* The maximum quantity allowed for purchase is 500.");
        //step 6: empty the cart and check the message.
        shoppingCartPage.clickEmptyBtn();
        softAssert.assertEquals(shoppingCartPage.getEmptyCartTitle(), "SHOPPING CART IS EMPTY");
        softAssert.assertAll();
    }
}
