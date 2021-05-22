package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmPage extends BasePage {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    public OrderConfirmPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle(){
        highlight(pageTitle);
        return pageTitle.getText().trim();
    }
}
