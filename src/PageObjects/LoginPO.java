package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPO extends BasePage {
    @FindBy (xpath = "//*[@name='user-name']")
    private WebElement usernameField;
    @FindBy (xpath = "//*[@name='password']")
    private WebElement passwordField;
    @FindBy (xpath = "//*[@name='login-button']")
    private WebElement loginButton;
    @FindBy (xpath = "//*[@data-test='error']")
    private WebElement loginErrorMessage;

    public LoginPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isUsernameFieldDisplayed() {
        return usernameField.isDisplayed();
    }

    public void inputUsernameFieldText(String username) {
        usernameField.sendKeys(username);
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }

    public void inputPasswordFieldText(String password) {
        passwordField.sendKeys(password);
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoginErrorMessageDisplayed() { return loginErrorMessage.isDisplayed(); }

    public void waitUntilLogInButtonIsInvisible() { wait.until(ExpectedConditions.invisibilityOf(loginButton)); }

    public String getLoginErrorMessage() { return loginErrorMessage.getText(); }
}
