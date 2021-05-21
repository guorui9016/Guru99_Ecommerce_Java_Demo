package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    @FindBy(name = "billing[firstname]")
    private WebElement txtFirstName;

    @FindBy(name = "billing[middlename]")
    private WebElement txtMiddlename;

    @FindBy(name = "billing[lastname]")
    private WebElement txtLastname;

    @FindBy(name = "billing[company]")
    private WebElement txtCompany;

    @FindBy(name = "billing[street][]")
    private WebElement txtAddress;

    @FindBy(name = "billing[city]")
    private WebElement txtCity;

    @FindBy(name = "billing[region_id]")
    private WebElement selectState;

    @FindBy(name = "billing[postcode]")
    private WebElement txtPost;

    @FindBy(name = "billing[country_id]")
    private WebElement selectCountry;

    @FindBy(name = "billing[telephone]")
    private WebElement txtPhone;

    @FindBy(css = "#billing-buttons-container button")
    private WebElement btnBillContinue;

    @FindBy(css = "#shipping-method-buttons-container button")
    private WebElement btnShipContinue;

    @FindBy(css = "#payment-buttons-container button")
    private WebElement btnPaymentContinue;

    @FindBy(css = "#review-buttons-container button")
    private WebElement btnReviewContinue;

    @FindBy(id = "p_method_checkmo")
    private WebElement moneyOrder;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void setAddress(String address){
        highlight(txtAddress);
        txtAddress.sendKeys(address);
    }

    public void setCity(String city){
        highlight(txtCity);
        txtCity.sendKeys(city);
    }

    public void setState(String state){
        highlight(selectState);
        Select selectState = (Select) this.selectState;
        selectState.selectByVisibleText(state);
    }

    public void setPostcode(String postcode){
        highlight(txtPost);
        txtPost.sendKeys(postcode);
    }

    public void setCountry(String country){
        highlight(selectCountry);
        Select selectCountry = (Select) this.selectCountry;
        selectCountry.selectByVisibleText(country);
    }

    public void setPhone(String phoneNum){
        highlight(txtPhone);
        txtPhone.sendKeys(phoneNum);
    }

    public void clickBillContinue(){
        highlight(btnBillContinue);
        btnBillContinue.click();
    }

     public void clickShipContinue(){
        wait.until(ExpectedConditions.visibilityOf(btnShipContinue));
        highlight(btnShipContinue);
        btnShipContinue.click();
     }

     public void clickMoneyOrder(){
        wait.until(ExpectedConditions.visibilityOf(moneyOrder));
        highlight(moneyOrder);
        moneyOrder.click();
     }

     public void clickPaymentContinue(){
        highlight(btnPaymentContinue);
        btnPaymentContinue.click();
     }

     public void clickReviewContinue(){
        wait.until(ExpectedConditions.visibilityOf(btnReviewContinue));
        highlight(btnReviewContinue);
        btnReviewContinue.click();
     }

}
