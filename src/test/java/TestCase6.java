import basePage.TestCaseBase;
import com.google.gson.JsonObject;
import helper.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageRepository.*;

public class TestCase6 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase6.class);

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void testCase06(JsonObject testData){
        //get all of the test data from the json
        String email = testData.get("email").getAsString();
        String password = testData.get("password").getAsString();
        String country = testData.get("country").getAsString();
        String state = testData.get("state").getAsString();
        String zip = testData.get("zip").getAsString();
        String address = testData.get("address").getAsString();
        String city = testData.get("city").getAsString();
        String telephone = testData.get("telephone").getAsString();
        logger.info("load test data: " + testData.toString());

        //1. Goto http://live.demoguru99.com
        HomePage homePage = new HomePage(driver);
        //2. Click on my account link
        MyAccountNoLoginPage myAccountNoLoginPage = homePage.clickMyAccountLink();
        //3. Loing in application
        myAccountNoLoginPage.setTxtEmail(email);
        myAccountNoLoginPage.setTxtPassword(password);
        MyAccountPage myAccountPage = myAccountNoLoginPage.clickLogin();
        //4. Click on my wishlist link
        WishListPage wishListPage = myAccountPage.clickWishListLink();
        //5. In next page, click add to cart link
        ShoppingCartPage shoppingCartPage = wishListPage.clickAddToCart();
        //6. Click proceed to checkout
        //7.enter shipping information
        shoppingCartPage.setSelectCountry(country);
        shoppingCartPage.setRegion(state);
        shoppingCartPage.setPostcode(zip);
        //8. click estimate
        shoppingCartPage.clickEstimate();
        //9. verify shipping cost generated
        String shippingFee = shoppingCartPage.getShippingFee();
        softAssert.assertEquals(shippingFee, "$5.00");
        //10. select shipping cost.update total
        String priceNoShipping = shoppingCartPage.getTotalPirce();
        shoppingCartPage.selectShippingFee();
        shoppingCartPage.clickUpdateTotal();
        //11. verify shipping cost is add t total
        String priceWithShipping = shoppingCartPage.getTotalPirce();
        softAssert.assertNotEquals(priceWithShipping, priceNoShipping);
        //12. click 'Proceed to checkout"
        CheckoutPage checkoutPage = shoppingCartPage.clickClickout();
        //13. Enter billing information
        checkoutPage.setAddress(address);
        checkoutPage.setCity(city);
        checkoutPage.setState(state);
        checkoutPage.setPostcode(zip);
        checkoutPage.setCountry(country);
        checkoutPage.setPhone(telephone);
        checkoutPage.clickBillContinue();
        //14. In shipping method, click continue
        checkoutPage.clickShipContinue();
        //15. In payment informtaion select 'Check/Money order' radio button. Click continue
        checkoutPage.clickMoneyOrder();
        checkoutPage.clickPaymentContinue();
        //16. Click 'Place order' button
        OrderConfirmPage orderConfirmPage = checkoutPage.clickReviewContinue();
        //17. Verify order is generated. Note the order number
        String pageTitle = orderConfirmPage.getPageTitle();
        softAssert.assertNotEquals(pageTitle, "YOUR ORDER HAS BEEN RECEIVED.");
        softAssert.assertAll();
    }
}
