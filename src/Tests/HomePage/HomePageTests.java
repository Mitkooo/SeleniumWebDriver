package Tests.HomePage;

import PageObjects.HomePO;
import PageObjects.LoginPO;
import Tests.Base.BaseTests;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static Constants.HomeConstants.*;
import static Constants.LoginConstants.*;

public class HomePageTests extends BaseTests {
    private HomePO homePO;

    @BeforeClass
    public void setUp() {
        initializeDriver(LOGIN_PAGE_URL);
        homePO = new HomePO(driver);
        LoginPO loginPO = new LoginPO(driver);

        loginPO.inputUsernameFieldText(STANDARD_USER);
        loginPO.inputPasswordFieldText(PASSWORD);
        loginPO.clickLoginButton();
        Assert.assertEquals("User is not logged in.", HOME_PAGE_URL, driver.getCurrentUrl());
    }

    @Test(priority = 1)
    @Description("Checks if user will be logged out when using the 'Logout' sidebar link.")
    public void testLoggingOutOfUserAccount() {
        Assert.assertTrue(homePO.isSidebarVisible(), "Side bar is not displayed");
        homePO.clickSidebarElement();
        homePO.selectLogOutOptionFromSideBar();
        Assert.assertEquals("User is not logged out.", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test(priority = 3)
    @Description("Checks if user will be redirected to the 'about' page when using the sidebar link.")
    public void testAboutSideBarLink() {
        Assert.assertTrue(homePO.isSidebarVisible(), "Side bar is not displayed");
        homePO.clickSidebarElement();
        homePO.selectAboutOptionFromSideBar();
        Assert.assertEquals("User is not redirected to the correct URL", ABOUT_PAGE_URL, driver.getCurrentUrl());
    }

    @Test(priority = 1)
    @Description("Checks if user can sort inventory items from Z to A.")
    public void testSortingProductsZToA() {
        // Assert sorting drop down is visible
        Assert.assertTrue(homePO.isProductSortingVisible(), "Product filter is not displayed");
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue(homePO.isSortingOptionCorrect(NAME_A_TO_Z), "Sorting is not the defaulted one.");
        // Get all products before they are sorted
        List<WebElement> beforeSorting = homePO.getProducts();
        // Get all products and assert if products are on page
        Assert.assertTrue(homePO.areProductsDisplayed(beforeSorting), "Not all products are displayed.");
        // Click on dropdown and sort items
        homePO.selectOptionFromProductSorter(NAME_Z_TO_A);
        // Get all sorted products
        List<WebElement> afterSorting = homePO.getProducts();
        // Assert of sorted products are on page and are sorted Z to A
        Assert.assertTrue(homePO.areProductsSortedByName(beforeSorting, afterSorting), "Products are the same, hence not sorted from Z to A.");
    }

    @Test(priority = 1)
    @Description("Checks if user can sort inventory items from low to high price.")
    public void testSortingProductsPriceLowToHigh() {
        // Assert sorting drop down is visible
        Assert.assertTrue(homePO.isProductSortingVisible(), "Product filter is not displayed");
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue(homePO.isSortingOptionCorrect(NAME_A_TO_Z), "Sorting is not the defaulted one.");
        // Click on dropdown and choose to sort items from low to high price
        homePO.selectOptionFromProductSorter(PRICE_LOW_TO_HIGH);
        // Get all products' price after sorting
        List<WebElement> afterSorting = homePO.getProducts();
        // Remove that '$' character from the price
        List<Double> afterPriceSorting= homePO.getPrices(afterSorting);
        // Assert if products before and after sorting are different, hence products have been correctly sorted
        Assert.assertTrue(homePO.areProductsSortedByPriceLowToHigh(afterPriceSorting), "Elements are not sorted correctly from low to high price");
    }

    @Test(priority = 1)
    @Description("Checks if user can sort inventory items from high to low price.")
    public void testSortingProductsPriceHighToLow() {
        // Assert sorting drop down is visible
        Assert.assertTrue(homePO.isProductSortingVisible(), "Product filter is not displayed");
        // Assert products are sorted A to Z before sorting
        Assert.assertTrue(homePO.isSortingOptionCorrect(NAME_A_TO_Z), "Sorting is not the defaulted one.");
        // Click on dropdown and choose to sort items from low to high price
        homePO.selectOptionFromProductSorter(PRICE_HIGH_TO_LOW);
        // Get all products' price after sorting
        List<WebElement> afterSorting = homePO.getProducts();
        // Remove that '$' character from the price
        List<Double> afterPriceSorting= homePO.getPrices(afterSorting);
        // Assert if products before and after sorting are different, hence products have been correctly sorted
        Assert.assertTrue(homePO.areProductsSortedByPriceHighToLow(afterPriceSorting), "Elements are not sorted correctly from low to high price");
    }
}
