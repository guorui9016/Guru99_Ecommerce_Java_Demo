package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(css = ".btn-share")
    private WebElement btnShare;

    @FindBy(css = ".success-msg span")
    private WebElement successMessage;

    @FindBy(css = "td[class*=customer-wishlist-item-cart] button")
    private WebElement btnAddToCart;

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        highlight(pageTitle);
        return pageTitle.getText();
    }

    public SharePage clickShare(){
        highlight(btnShare);
        btnShare.click();
        return new SharePage(driver);
    }

    public String getSuccessMessageText(){
        highlight(successMessage);
        return successMessage.getText().trim();
    }

    public ShoppingCartPage clickAddToCart(){
        highlight(btnAddToCart);
        btnAddToCart.click();
        return new ShoppingCartPage(driver);
    }
}
