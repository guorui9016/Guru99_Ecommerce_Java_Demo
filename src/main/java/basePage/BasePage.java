package basePage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageRepository.MobilePage;
import pageRepository.TvPage;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = "#nav li[class*=nav-1]")
    private  WebElement mobileLink;

    @FindBy(css = "#nav li[class*=nav-2]")
    private WebElement tvLink;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    protected void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, "border: 2px solid red;");
    }

    public MobilePage clickMobileLink(){
        highlight(mobileLink);
        mobileLink.click();
        return new MobilePage(driver);
    }

    public TvPage clickTVLink(){
        highlight(tvLink);
        tvLink.click();
        return new TvPage(driver);
    }
}
