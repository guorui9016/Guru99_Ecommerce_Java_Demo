package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparedPage extends BasePage {
    String mainWindow;

    @FindBy(css = "div[class^=page-title] h1")
    private WebElement title;

    @FindBy(css = "button[title='Close Window']")
    private WebElement btnCloseWindows;

    public ComparedPage(WebDriver driver) {
        super(driver);
    }

    public ComparedPage(WebDriver driver, String mainWindow) {
        super(driver);
        this.mainWindow = mainWindow;
    }

    public String getTitle(){
        return title.getText().trim();
    }

    public void closeWindow(){
        btnCloseWindows.click();
        driver.switchTo().window(mainWindow);
    }
}
