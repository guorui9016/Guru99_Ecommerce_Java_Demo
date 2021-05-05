package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage {
    @FindBy(css = "input[title= Qty]")
    private WebElement productQty;

    @FindBy(css = "button[title=Update]")
    private WebElement btnUpdate;

    @FindBy(css = ".error-msg span")
    private WebElement errorMsg;

    @FindBy(css = "p[class^=item-msg]")
    private WebElement errorMsgDetails;

    @FindBy(id = "empty_cart_button")
    private WebElement btnEmptyCart;

    @FindBy(css = ".page-title h1")
    private WebElement emptyCartTitle;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMsg(){
        highlight(errorMsg);
        return errorMsg.getText();
    }

    public String getErrorMsgDetails(){
        highlight(errorMsgDetails);
        return errorMsgDetails.getText();
    }
    public void setProductQty(int value){
        highlight(productQty);
        productQty.click();
        productQty.clear();
        productQty.sendKeys(String.valueOf(value));
    }

    public void clickEmptyBtn(){
        highlight(btnEmptyCart);
        btnEmptyCart.click();
    }

    public String getEmptyCartTitle(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emptyCartTitle));
        highlight(emptyCartTitle);
        return emptyCartTitle.getText();
    }

    public WebElement getBtnUpdate() {
        return btnUpdate;
    }
}
