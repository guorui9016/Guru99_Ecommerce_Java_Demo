package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement title;

    @FindBy(css = ".form-list input[type=password]")
    private WebElement txtPasswordInput;

    @FindBy(css = "div[class*=new-users] .button")
    private  WebElement btnCreateAccount;

    @FindBy(css = ".success-msg span")
    private WebElement successMsg;

    @FindBy(css = ".hello strong")
    private WebElement welcomeMsg;

    @FindBy(xpath = "//div[@class='block-content']//a[text()='My Wishlist']")
    private WebElement myWishlist;

    @FindBy(css = ".link-reorder")
    private WebElement reorderLink;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){
        highlight(title);
        return title.getText();
    }

    public String getSuccessMsg(){
        highlight(successMsg);
        return successMsg.getText();
    }

    public String getWelcomeMsg(){
        highlight(welcomeMsg);
        return welcomeMsg.getText();
    }

    public WishListPage clickWishListLink(){
        wait.until(ExpectedConditions.visibilityOf(myWishlist));
        highlight(myWishlist);
        myWishlist.click();
        return new WishListPage(driver);
    }

    public ShoppingCartPage clickReorderLink(){
        highlight(reorderLink);
        reorderLink.click();
        return new ShoppingCartPage(driver);
    }
}
