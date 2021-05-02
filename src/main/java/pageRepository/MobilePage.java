package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MobilePage extends BasePage {
    @FindBy(css = "div[class^= page-title] h1")
    private WebElement pageTitle;

    @FindBy(css = ".sort-by select")
    private WebElement sortBySelect;

    @FindBy(css = "ul[class^=products-grid]")
    private WebElement productsList;

    public MobilePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public void setSortBySelect(String itemText){
        highlight(sortBySelect);
        Select select = new Select(sortBySelect);
        select.selectByVisibleText(itemText);
    }

    public String[] getAllProductsName(){
        List<WebElement> nameElements = productsList.findElements(By.cssSelector(".product-name a"));
        String[] names = new String[nameElements.size()];
        for (int i = 0; i < nameElements.size(); i++) {
            highlight(nameElements.get(i));
            names[i] = nameElements.get(i).getText();
        }
        return names;
    }
}
