import basePage.TestCaseBase;
import com.google.gson.JsonObject;
import helper.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageRepository.HomePage;
import pageRepository.MobilePage;
import pageRepository.ShoppingCartPage;

public class TestCase8 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase8.class);

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void testCase08(JsonObject jsonData){
        String coupon = jsonData.get("coupon").getAsString();
        //1. Go to http://live. Demoguru99.com
        HomePage homePage = new HomePage(driver);
        //2. Go to Mobile and add iphone to cart
        MobilePage mobilePage = homePage.clickMobileLink();
        WebElement iphone = mobilePage.getProductByName("IPHONE");
        ShoppingCartPage shoppingCartPage = mobilePage.addProductIntoCart(iphone);
        // 3. Enter coupon code
        shoppingCartPage.setCoupon(coupon);
        shoppingCartPage.clickBtnApply();
        //4. Verify the discount generated
        String successMsg = shoppingCartPage.getCouponSuccessMsg();
        softAssert.assertTrue(successMsg.contains("was applied"));
        softAssert.assertAll();
    }
}
