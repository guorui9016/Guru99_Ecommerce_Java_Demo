import basePage.TestCaseBase;
import com.google.gson.JsonObject;
import helper.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageRepository.*;

public class TestCase5 extends TestCaseBase {
    Logger logger = LogManager.getLogger(TestCase5.class);

    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    public void testCase05(JsonObject testData){
        //get all of the test data from the json
        String firstName = testData.get("first_name").getAsString();
        String middleName = testData.get("middle_name").getAsString();
        String lastName = testData.get("last_name").getAsString();
        String email = testData.get("email").getAsString();
        String password = testData.get("password").getAsString();
        String expectTitle = testData.get("expect_title").getAsString();
        String productName = testData.get("product").getAsString();
        String sharedEmail = testData.get("shared_email").getAsString();
        String sharedMsg = testData.get("shared_msg").getAsString();
        logger.info("load test data: " + testData.toString());

        //step 1: create the new account
        HomePage homePage = new HomePage(driver);
        MyAccountNoLoginPage myAccountPage = homePage.clickMyAccountLink();
        myAccountPage.setTxtEmail(email);
        myAccountPage.setTxtPassword(password);
        MyAccountPage myAccountLoginPage = myAccountPage.clickLogin();
        String expectWelcomeMsg;
        if (middleName.isEmpty()){
            expectWelcomeMsg = "Hello, "+ firstName+ " " +lastName + "!";
        }else {
            expectWelcomeMsg = "Hello, "+ firstName +" "+middleName+" "+lastName+"!";
        }
        //step 2: check the registration is done
        softAssert.assertEquals(myAccountLoginPage.getTitle(), expectTitle);
        softAssert.assertEquals(myAccountLoginPage.getWelcomeMsg(), expectWelcomeMsg);
        //step 3: add the product to wishlist
        TvPage tvPage = myAccountLoginPage.clickTVLink();
        WebElement product = tvPage.getProductByName(productName);
        WishListPage wishListPage = tvPage.clickAddToWishList(product);
        //step 4: share the product
        SharePage sharePage = wishListPage.clickShare();
        sharePage.setTxtEmail(sharedEmail);
        sharePage.setTxtMessage(sharedMsg);
        wishListPage = sharePage.clickSharedButton();
        //step 5: check the shared message
        String successMessageText = wishListPage.getSuccessMessageText();
        softAssert.assertEquals(successMessageText, "Your Wishlist has been shared.");
        softAssert.assertAll();
    }
}
