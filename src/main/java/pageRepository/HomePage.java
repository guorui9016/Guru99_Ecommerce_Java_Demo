package pageRepository;

import basePage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = ".page-title h2")
    private WebElement pageTitle;

    @FindBy(css = "li[class*=nav-1]")
    private  WebElement mobileLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleText() {
        highlight(pageTitle);
        return pageTitle.getText();
    }

    public MobilePage clickMobileLink(){
        highlight(mobileLink);
        mobileLink.click();
        return new MobilePage(driver);
    }
}
