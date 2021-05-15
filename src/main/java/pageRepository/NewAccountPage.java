package pageRepository;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewAccountPage extends BasePage {

    @FindBy(id = "firstname")
    private WebElement txtFirstName;

    @FindBy(id = "middlename")
    private WebElement txtMiddleName;

    @FindBy(id = "lastname")
    private  WebElement txtLastName;

    @FindBy(id = "email_address")
    private  WebElement txtEmailAddress;

    @FindBy(id = "password")
    private  WebElement txtPassword;

    @FindBy(id = "confirmation")
    private  WebElement txtConfirmationPassword;

    @FindBy(css = "button[title=Register]")
    private WebElement btnRegister;

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName){
        highlight(txtFirstName);
        txtFirstName.sendKeys(firstName);
    }

    public void setMiddlename(String middleName){
        highlight(txtMiddleName);
        txtMiddleName.sendKeys(middleName);
    }

    public void setLastname(String lastName){
        highlight(txtLastName);
        txtLastName.sendKeys(lastName);
    }

    public void setEmailAddress(String email){
        highlight(txtEmailAddress);
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String password){
        highlight(txtPassword);
        txtPassword.sendKeys(password);
    }

    public void setConfirPassword(String confirmationEmail){
        highlight(txtConfirmationPassword);
        txtConfirmationPassword.sendKeys(confirmationEmail);
    }

    public MyAccountPage clickRegister(){
        highlight(btnRegister);
        btnRegister.click();
        return new MyAccountPage(driver);
    }
}
