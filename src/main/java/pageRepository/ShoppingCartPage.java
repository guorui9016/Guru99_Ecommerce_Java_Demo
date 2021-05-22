package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(css = ".method-checkout-cart-methods-onepage-bottom button")
    private WebElement btnCheckout;

    @FindBy(id = "country")
    private WebElement selectCountry;

    @FindBy(id = "region_id")
    private WebElement selectRegion;

    @FindBy(id = "postcode")
    private WebElement txtPostcode;

    @FindBy(css = "button[title='Estimate']")
    private WebElement btnEstimate;

    @FindBy(css = "#co-shipping-method-form .price")
    private WebElement tvPrice;

    @FindBy(id = "s_method_flatrate_flatrate")
    private WebElement rdoShippingFee;

    @FindBy(css = ".buttons-set button[name='do']")
    private WebElement btnUpdateTotal;

    @FindBy(css = "#shopping-cart-totals-table tfoot .price")
    private WebElement tvTotalPirce;

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
        highlight(btnUpdate);
        return btnUpdate;
    }

    public void setSelectCountry(String country){
        highlight(selectCountry);
        Select selectCountry = new Select(this.selectCountry);
        selectCountry.selectByVisibleText(country);
    }

    public void setRegion(String region){
        highlight(selectRegion);
        Select selectRegion = new Select(this.selectRegion);
        selectRegion.selectByVisibleText(region);
    }

    public void setPostcode(String postcode){
        highlight(txtPostcode);
        txtPostcode.clear();
        txtPostcode.sendKeys(postcode);
    }

    public void clickEstimate(){
        highlight(btnEstimate);
        btnEstimate.click();
    }

    public String getShippingFee(){
        wait.until(ExpectedConditions.visibilityOf(tvPrice));
        highlight(tvPrice);
        return tvPrice.getText();
    }

    public void selectShippingFee(){
        highlight(rdoShippingFee);
        rdoShippingFee.click();
    }

    public void clickUpdateTotal(){
        highlight(btnUpdateTotal);
        btnUpdateTotal.click();
    }

    public String getTotalPirce(){
        highlight(tvTotalPirce);
        return tvTotalPirce.getText();
    }

    public CheckoutPage clickClickout(){
        highlight(btnCheckout);
        btnCheckout.click();
        return new CheckoutPage(driver);
    }
}
