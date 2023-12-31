package Tests;

import PageObjects.HomePO;
import PageObjects.LoginPO;
import PageObjects.ShopCartPO;
import Tests.Base.BaseTests;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Constants.HomeConstants.*;
import static Constants.LoginConstants.*;

public class ShopCartPageTests extends BaseTests {
    private HomePO homePO;
    private ShopCartPO cartPO;

    @BeforeMethod
    public void setUp() {
        initializeDriver(LOGIN_PAGE_URL);
        homePO = new HomePO(driver);
        cartPO = new ShopCartPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, "User is not logged in.");
    }

    @Test(priority = 1)
    @Description("Test adding an item to the cart and verify item is displayed and correct one is added to the cart")
    public void testAddingSingleProductToCart() {
        // Assert "Add to cart button" is displayed
        Assert.assertTrue(homePO.isBikeLightAddToCartBtnDisplayed(), "Add to cart button is not displayed.");
        // Click on the button adding a single product to the cart
        homePO.clickAddBikeLightToCart();
        // Assert shopping cart button in the upper right corner is display
        Assert.assertTrue(homePO.isShoppingCartButtonDisplayed(), "Shopping cart button is not displayed.");
        // Click on shopping cart button
        homePO.clickShoppingCartButton();
        // Assert correct product is successfully added to the cart by comparing names
        Assert.assertTrue(cartPO.areAddedProductsNumberCorrect(), "Number of added products is not correct.");
        Assert.assertTrue(cartPO.isProductNameCorrect(BIKE_LIGHT), "Names doesn't match");
    }

    @Test(priority = 2)
    @Description("Verify multiple products will be displayed when added to shopping cart.")
    public void testAddingMultipleProducts() {
        Assert.assertTrue(homePO.isBikeLightAddToCartBtnDisplayed(), "Bike light Add to cart button is not displayed.");
        Assert.assertTrue(homePO.isBackpackAddToCartBtnDisplayed(), "Backpack Add to cart button is not displayed.");
        Assert.assertTrue(homePO.isBoltTShirtAddToCartBtnDisplayed(), "Bolt T-Shirt Add to cart button is not displayed.");
        homePO.clickAddBikeLightToCart();
        homePO.clickBoltTShirtAddToCartBtn();
        homePO.clickBackpackAddToCartBtn();
        Assert.assertTrue(homePO.isShoppingCartButtonDisplayed(), "Shopping cart button is not displayed.");
        homePO.clickShoppingCartButton();
        Assert.assertTrue(cartPO.areAddedProductsNumberCorrect(), "Number of added products is not correct.");
    }

    @Test(priority = 1)
    @Description("Test adding a product to cart and removing it")
    public void testRemovingProductFromCart() {
        // Assert "Add to cart button" is displayed
        Assert.assertTrue(homePO.isBikeLightAddToCartBtnDisplayed(), "Add to cart button is not displayed.");
        // Add product to cart
        homePO.clickAddBikeLightToCart();
        // Open cart
        homePO.clickShoppingCartButton();
        // Assert product name is correct
        cartPO.isProductNameCorrect(BIKE_LIGHT);
        // Remove product from cart
        cartPO.clickRemoveButton();
        // assert product is removed
        Assert.assertTrue(cartPO.isProductRemoved(), "Item is still in cart");
    }
}