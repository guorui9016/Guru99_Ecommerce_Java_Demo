package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MobilePage extends BasePage {
    @FindBy(css = "div[class^= page-title] h1")
    private WebElement pageTitle;

    @FindBy(css = ".sort-by select")
    private WebElement sortBySelect;

    @FindBy(css = "ul[class^=products-grid]")
    private WebElement productsList;

    @FindBy(xpath = "//a[@title=\"Xperia\"] /..")
    private WebElement productSony;

    @FindAll(
            @FindBy(css = "li[class^=item]")
    )
    private List<WebElement> products;

    @FindBy(css = "div[class*=block-compare]")
    private WebElement compareBox;

    @FindBy(css = "button[title=Compare]")
    private WebElement btnCompare;

    public MobilePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public WebElement getProductSony(){
        highlight(productSony);
        return productSony;
    }

    public ProductDetailsPage gotoProducts(WebElement product){
        WebElement productLink = product.findElement(By.className("product-image"));
        highlight(productLink);
        productLink.click();
        return new ProductDetailsPage(driver);
    }

    public ShoppingCartPage addProductIntoCart(WebElement product){
        WebElement btnAddToCart = product.findElement(By.cssSelector("button"));
        highlight(btnAddToCart);
        btnAddToCart.click();
        return new ShoppingCartPage(driver);
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

    public WebElement getProductByName(String productName){
        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector(".product-name a")).getText().trim();
            if (productName.equalsIgnoreCase(name)) {
                highlight(product);
                return product;
            }
        }
        return null;
    }

    public void addProductToCompareList(WebElement product){
        String productName = product.findElement(By.cssSelector(".product-name a")).getText();
        highlight(product.findElement(By.cssSelector(".product-name a")));
        product.findElement(By.className("link-compare")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*=block-compare]")));
        String xpath = "//ol[@id='compare-items']//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"+ productName.toLowerCase() + "')]";
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
        highlight(driver.findElement(By.xpath(xpath)));
    }

    public ComparedPage clickCompareButton(){
        String main = driver.getWindowHandle();
        btnCompare.click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if (!main.equals(s)){
                driver.switchTo().window(s);
                return new ComparedPage(driver, main);
            }
        }
        return null;
    }
}
