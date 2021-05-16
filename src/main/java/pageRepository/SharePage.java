package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SharePage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    @FindBy(id = "email_address")
    private WebElement txtEmail;

    @FindBy(id = "message")
    private WebElement txtMessage;

    @FindBy(css = ".form-buttons .button")
    private WebElement btnShared;

    public SharePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        highlight(pageTitle);
        return pageTitle.getText();
    }

    public void setTxtEmail(String email){
        highlight(txtEmail);
        txtEmail.sendKeys(email);
    }

    public void setTxtMessage(String msg){
        highlight(txtMessage);
        txtMessage.sendKeys(msg);
    }

    public WishListPage clickSharedButton(){
        highlight(btnShared);
        btnShared.click();
        return new WishListPage(driver);
    }
}
