package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountNoLoginPage extends BasePage {

    @FindBy(css = ".form-list input[type=email]")
    private WebElement txtEmail;

    @FindBy(css = ".form-list input[type=password]")
    private WebElement txtPassword;

    @FindBy(css = "div[class*=new-users] .button")
    private  WebElement btnCreateAccount;

    @FindBy(id = "send2")
    private WebElement btnLogin;

    public MyAccountNoLoginPage(WebDriver driver) {
        super(driver);
    }

    public NewAccountPage clickNewAccount(){
        highlight(btnCreateAccount);
        btnCreateAccount.click();
        return new NewAccountPage(driver);
    }

    public void setTxtEmail(String email){
        highlight(txtEmail);
        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String pwd){
        highlight(txtPassword);
        txtPassword.sendKeys(pwd);
    }

    public MyAccountPage clickLogin(){
        highlight(btnLogin);
        btnLogin.click();
        return new MyAccountPage(driver);
    }
}
