package Tests.HomePage;

import PageObjects.HomePO;
import PageObjects.LoginPO;
import PageObjects.ShopCartPO;
import Tests.Base.BaseTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Constants.HomeConstants.*;
import static Constants.LoginConstants.*;

public class HomePageTests extends BaseTests {
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
    @DisplayName("Checks if user will be logged out when using the 'Logout' sidebar link.")
    public void testLoggingOutOfUserAccount() {
        Assert.assertTrue("Side bar is not displayed",homePO.isSidebarVisible());
        homePO.clickSidebarElement();
        homePO.selectLogOutOptionFromSideBar();
        Assert.assertEquals("User is not logged out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checks if user will be redirected to the 'about' page when using the sidebar link.")
    public void testAboutSideBarLink() {
        Assert.assertTrue("Side bar is not displayed",homePO.isSidebarVisible());
        homePO.clickSidebarElement();
        homePO.selectAboutOptionFromSideBar();
        Assert.assertEquals("User is not redirected to the correct URL", ABOUT_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checks if user can sort inventory items from Z to A.")
    public void testSortingProductsZToA() {
        // Assert sorting drop down is visible
        Assert.assertTrue("Product filter is not displayed",homePO.isProductSortingVisible());
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue("Sorting is not the defaulted one.", homePO.isSortingOptionCorrect(NAME_A_TO_Z));
        // Get all products before they are sorted
        List<WebElement> beforeSorting = homePO.getProducts();
        // Get all products and assert if products are on page
        Assert.assertTrue("Not all products are displayed.", homePO.areProductsDisplayed(beforeSorting));
        // Click on dropdown and sort items
        homePO.selectOptionFromProductSorter(NAME_Z_TO_A);
        // Get all sorted products
        List<WebElement> afterSorting = homePO.getProducts();
        // Assert of sorted products are on page and are sorted Z to A
        Assert.assertTrue("Products are the same, hence not sorted from Z to A.", homePO.areProductsSortedByName(beforeSorting, afterSorting));
    }

    @Test
    @DisplayName("Checks if user can sort inventory items from low to high price.")
    public void testSortingProductsPriceLowToHigh() {
        // Assert sorting drop down is visible
        Assert.assertTrue("Product filter is not displayed",homePO.isProductSortingVisible());
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue("Sorting is not the defaulted one.", homePO.isSortingOptionCorrect(NAME_A_TO_Z));
        // Click on dropdown and choose to sort items from low to high price
        homePO.selectOptionFromProductSorter(PRICE_LOW_TO_HIGH);
        // Get all products' price after sorting
        List<WebElement> afterSorting = homePO.getProducts();
        // Remove that '$' character from the price
        List<Double> afterPriceSorting= homePO.getPrices(afterSorting);
        // Assert if products before and after sorting are different, hence products have been correctly sorted
        Assert.assertTrue("Elements are not sorted correctly from low to high price", homePO.areProductsSortedByPriceLowToHigh(afterPriceSorting));
    }

    @Test
    @DisplayName("Checks if user can sort inventory items from high to low price.")
    public void testSortingProductsPriceHighToLow() {
        // Assert sorting drop down is visible
        Assert.assertTrue("Product filter is not displayed",homePO.isProductSortingVisible());
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue("Sorting is not the defaulted one.", homePO.isSortingOptionCorrect(NAME_A_TO_Z));
        // Click on dropdown and choose to sort items from low to high price
        homePO.selectOptionFromProductSorter(PRICE_HIGH_TO_LOW);
        // Get all products' price after sorting
        List<WebElement> afterSorting = homePO.getProducts();
        // Remove that '$' character from the price
        List<Double> afterPriceSorting= homePO.getPrices(afterSorting);
        // Assert if products before and after sorting are different, hence products have been correctly sorted
        Assert.assertTrue("Elements are not sorted correctly from low to high price", homePO.areProductsSortedByPriceHighToLow(afterPriceSorting));
    }
}
