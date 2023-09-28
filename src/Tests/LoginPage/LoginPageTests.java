package Tests.LoginPage;

import PageObjects.LoginPO;
import Tests.Base.BaseTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static Constants.LoginConstants.*;

public class LoginPageTests extends BaseTests {

    private LoginPO loginPO;

    @Before
    public void setUp() {
        initializeDriver(LOGIN_PAGE_URL);
        loginPO = new LoginPO(driver);
        Assert.assertTrue("Username field is not displayed",loginPO.isUsernameFieldDisplayed());
        Assert.assertTrue("Password field is not displayed", loginPO.isPasswordFieldDisplayed());
        Assert.assertTrue("Login button is not displayed", loginPO.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Test login functionality with Standard_user credentials")
    public void testLoginWithStandardUserCredentials() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals("User is not logged in.", HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test login functionality with problem_user credentials")
    public void testLoginWithProblemUserCredentials() {
        loginPO.inputUsernameFieldText(PROBLEM_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals("User is not logged in.", HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test login functionality with locked_out_user credentials")
    public void testLoginWithLockedOutUserCredentials() {
        loginPO.inputUsernameFieldText(LOCKED_OUT_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Locked out user error message is not as expected", LOCKED_OUT_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with performance_glitch_user credentials")
    public void testLoginWithPerformanceGlitchUser() {
        loginPO.inputUsernameFieldText(PERFORMANCE_GLITCH_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        loginPO.waitUntilLogInButtonIsInvisible();
        Assert.assertEquals("User is not logged in.", HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test login functionality with no username")
    public void testLoginWithEmptyUsernameField() {
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Empty username error message is not as expected", EMPTY_USERNAME_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with no password")
    public void testLoginWithEmptyPasswordField() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Empty password error message is not as expected", EMPTY_PASSWORD_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with no username or password")
    public void testLoginWithAllFieldsEmpty() {
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Empty username error message is not as expected", EMPTY_USERNAME_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with invalid credentials that aren't the provided")
    public void testLoginWithCredentialsDifferentThanProvided() {
        loginPO.inputUsernameFieldText(INVALID_USER);
        loginPO.inputPasswordFieldText(INVALID_PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with valid username and invalid password that isn't the provided")
    public void testLoginWithValidUsernameAndInvalidPassword() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(INVALID_PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with invalid username that isn't the provided and valid password")
    public void testLoginWithInvalidUsernameAndValidPassword() {
        loginPO.inputUsernameFieldText(INVALID_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with username in both fields")
    public void testLoginWithUsernameInAllFields() {
        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with username in both fields")
    public void testLoginWithPasswordInAllFields() {
        loginPO.inputUsernameFieldText(PASSWORD);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

    @Test
    @DisplayName("Test login functionality with swapped credentials - password in username field / username in password field")
    public void testLoginWithSwappedCredentials() {
        loginPO.inputUsernameFieldText(PASSWORD);
        loginPO.inputPasswordFieldText(STANDARD_USER);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in, locked out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
        Assert.assertTrue("No error message is displayed.",loginPO.isLoginErrorMessageDisplayed());
        Assert.assertEquals("Invalid user error message is not as expected", INVALID_USER_ERROR_MESSAGE, loginPO.getLoginErrorMessage());
    }

}
