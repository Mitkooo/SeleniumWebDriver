package Tests;

import PageObjects.HomePO;
import PageObjects.LoginPO;
import PageObjects.ShopCartPO;
import Tests.Base.BaseTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static Constants.HomeConstants.*;
import static Constants.LoginConstants.*;

public class ShopCartTests extends BaseTests {
    private HomePO homePO;
    private ShopCartPO cartPO;

    @Before
    public void setUp() {
        initializeDriver(LOGIN_PAGE_URL);
        homePO = new HomePO(driver);
        cartPO = new ShopCartPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in.", HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test adding an item to the cart and verify item is displayed and correct one is added to the cart")
    public void testAddingSingleProductToCart() {
        // Assert "Add to cart button" is displayed
        Assert.assertTrue("Add to cart button is not displayed.", homePO.isBikeLightAddToCartBtnDisplayed());
        // Click on the button adding a single product to the cart
        homePO.clickAddBikeLightToCart();
        // Assert shopping cart button in the upper right corner is display
        Assert.assertTrue("Shopping cart button is not displayed.", homePO.isShoppingCartButtonDisplayed());
        // Click on shopping cart button
        homePO.clickAddBikeLightToCart();
        // Assert correct product is successfully added to the cart by comparing names
        Assert.assertTrue("Number of added products is not correct.", cartPO.areAddedProductsNumberCorrect());
        Assert.assertTrue("Names doesn't match", cartPO.isProductNameCorrect(BIKE_LIGHT));
    }

    @Test
    @DisplayName("Bim bim bam bam")
    public void testAddingMultipleProductsToCart() {

    }
}
