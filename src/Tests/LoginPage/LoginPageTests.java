package Tests.LoginPage;

import PageObjects.LoginPO;
import Tests.Base.BaseTests;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import static Constants.LoginConstants.*;

public class LoginPageTests extends BaseTests {

    private LoginPO loginPO;

    @BeforeMethod
    public void setUp() {
        initializeDriver(LOGIN_PAGE_URL);
        loginPO = new LoginPO(driver);
        Assert.assertTrue(loginPO.isUsernameFieldDisplayed(), "Username field is not displayed");
        Assert.assertTrue(loginPO.isPasswordFieldDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginPO.isLoginButtonDisplayed(), "Login button is not displayed");
    }

    @Test(priority = 1)
    @Description("Test login functionality with Standard_user credentials")
    public void testLoginWithStandardUserCredentials() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "User is not logged in." );
    }

    @Test(priority = 2)
    @Description("Test login functionality with problem_user credentials")
    public void testLoginWithProblemUserCredentials() {
        loginPO.inputUsernameFieldText(PROBLEM_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "User is not logged in." );
        Assert.assertTrue(loginPO.isProblemUserImageDisplayed(), "Image is problem user image");
    }

    @Test(priority = 1)
    @Description("Test login functionality with locked_out_user credentials")
    public void testLoginWithLockedOutUserCredentials() {
        loginPO.inputUsernameFieldText(LOCKED_OUT_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), LOCKED_OUT_USER_ERROR_MESSAGE,"Locked out user error message is not as expected" );
    }

    @Test(priority = 2)
    @Description("Test login functionality with performance_glitch_user credentials")
    public void testLoginWithPerformanceGlitchUser() {
        loginPO.inputUsernameFieldText(PERFORMANCE_GLITCH_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "User is not logged in." );
    }

    @Test(priority = 2)
    @Description("Test login functionality with no username")
    public void testLoginWithEmptyUsernameField() {
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), EMPTY_USERNAME_ERROR_MESSAGE,"Empty username error message is not as expected");
    }

    @Test(priority = 1)
    @Description("Test login functionality with no password")
    public void testLoginWithEmptyPasswordField() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), EMPTY_PASSWORD_ERROR_MESSAGE,"Empty password error message is not as expected");
    }

    @Test(priority = 3)
    @Description("Test login functionality with no username or password")
    public void testLoginWithAllFieldsEmpty() {
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), EMPTY_USERNAME_ERROR_MESSAGE,"Empty username error message is not as expected");
    }

    @Test(priority = 3)
    @Description("Test login functionality with invalid credentials that aren't the provided")
    public void testLoginWithCredentialsDifferentThanProvided() {
        loginPO.inputUsernameFieldText(INVALID_USER);
        loginPO.inputPasswordFieldText(INVALID_PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }

    @Test(priority = 2)
    @Description("Test login functionality with valid username and invalid password that isn't the provided")
    public void testLoginWithValidUsernameAndInvalidPassword() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(INVALID_PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }

    @Test(priority = 2)
    @Description("Test login functionality with invalid username that isn't the provided and valid password")
    public void testLoginWithInvalidUsernameAndValidPassword() {
        loginPO.inputUsernameFieldText(INVALID_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }

    @Test(priority = 3)
    @Description("Test login functionality with username in both fields")
    public void testLoginWithUsernameInAllFields() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }

    @Test(priority = 3)
    @Description("Test login functionality with password in both fields")
    public void testLoginWithPasswordInAllFields() {
        loginPO.inputUsernameFieldText(PASSWORD);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }

    @Test(priority = 3)
    @Description("Test login functionality with swapped credentials - password in username field / username in password field")
    public void testLoginWithSwappedCredentials() {
        loginPO.inputUsernameFieldText(PASSWORD);
        loginPO.inputPasswordFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL,"User is not logged in, locked out." );
        Assert.assertTrue(loginPO.isLoginErrorMessageDisplayed(), "No error message is displayed.");
        Assert.assertEquals(loginPO.getLoginErrorMessage(), INVALID_USER_ERROR_MESSAGE,"Invalid user error message is not as expected");
    }
}
