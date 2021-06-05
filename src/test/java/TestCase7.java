import basePage.TestCaseBase;
import com.google.gson.JsonObject;
import helper.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageRepository.*;

/**
 * Verify you are able to change or reorder previously added product
 */

public class TestCase7 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase7.class);

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void testCase07(JsonObject testData){
        //load data
        String email = testData.get("email").getAsString();
        String password = testData.get("password").getAsString();
        int qty = testData.get("qty").getAsInt();
        String country = testData.get("country").getAsString();
        String state = testData.get("state").getAsString();
        String zip = testData.get("zip").getAsString();
        String address = testData.get("address").getAsString();
        String city = testData.get("city").getAsString();
        String telephone = testData.get("telephone").getAsString();
        logger.info("load test data: " + testData.toString());
        //1. Go to http://live. Demoguru99.com
        HomePage homePage = new HomePage(driver);
        //2. Click on my account link
        MyAccountNoLoginPage myAccountNoLoginPage = homePage.clickMyAccountLink();
        //3. Login in application using previously created credential
        myAccountNoLoginPage.setTxtEmail(email);
        myAccountNoLoginPage.setTxtPassword(password);
        MyAccountPage myAccountPage = myAccountNoLoginPage.clickLogin();
        //4. Click on 'Reorder' link, change QTY & click Update
        ShoppingCartPage shoppingCartPage = myAccountPage.clickReorderLink();
        String totalPirce = shoppingCartPage.getTotalPirce();
        shoppingCartPage.setProductQty(qty);
        shoppingCartPage.clickBtnUpdate();
        String currentTotalPirce = shoppingCartPage.getTotalPirce();
        //5. Verify Grand total is changed
        //Check: 1) Grand total is changed
        softAssert.assertNotEquals(totalPirce, currentTotalPirce);
        //6. Complete billing & shipping information
        CheckoutPage checkoutPage = shoppingCartPage.clickCheckout();
        checkoutPage.seletNewAddress();
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
        //7. Verify order is generated and note the order number
        String orderNumber = orderConfirmPage.getOrderNumber();
        //check: 2) Order number is generated
        softAssert.assertTrue(!orderNumber.isEmpty());
        softAssert.assertAll();
    }
}
