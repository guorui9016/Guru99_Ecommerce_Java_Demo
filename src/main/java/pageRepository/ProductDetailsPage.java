package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

    @FindBy(className = "price")
    private WebElement price;


    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPrice() {
        highlight(price);
        return price;
    }
}
