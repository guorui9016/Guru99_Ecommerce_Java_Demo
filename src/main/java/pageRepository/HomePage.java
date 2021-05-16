package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = ".page-title h2")
    private WebElement pageTitle;


    @FindBy(css = ".footer a[title='My Account']")
    private  WebElement myAccount;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        highlight(pageTitle);
        return pageTitle.getText();
    }



    public MyAccountNoLoginPage clickMyAccountLink(){
        highlight(myAccount);
        myAccount.click();
        return new MyAccountNoLoginPage(driver);
    }
}
