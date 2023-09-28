package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePO extends BasePage {
    @FindBy (xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement sideBar;
    @FindBy (xpath = "//*[@id='logout_sidebar_link']")
    private WebElement logout;
    @FindBy (xpath = "//*[@id='inventory_sidebar_link']")
    private WebElement allItems;
    @FindBy (xpath = "//*[@id='about_sidebar_link']")
    private WebElement about;
    @FindBy (xpath = "//*[@id='reset_sidebar_link']")
    private WebElement resetAppState;
    @FindBy (xpath = "//*[@class='product_sort_container']")
    private WebElement productSortingDropDown;
    @FindBy (xpath = "//*[@class='shopping_cart_link']")
    private WebElement shoppingCartButton;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement backpackCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement bikeLightCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement boltTShirtCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement fleeceJacketCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-onesie']")
    private WebElement onesieCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")
    private WebElement redTShirtCartBtn;

    public HomePO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSidebarVisible() {
        return sideBar.isDisplayed();
    }

    public boolean isLogOutOptionVisible() {
        return logout.isDisplayed();
    }

    public boolean isBikeLightAddToCartBtnDisplayed() {
        return bikeLightCartBtn.isDisplayed();
    }

    public boolean isBackPackAddBnDisplayed() {
        return backpackCartBtn.isDisplayed();
    }

    public boolean isBolTTShirtAddToCartBtnDisplayed() {
        return boltTShirtCartBtn.isDisplayed();
    }

    public boolean isFleeceJacketAddToCartBthDisplayed() {
        return fleeceJacketCartBtn.isDisplayed();
    }

    public boolean isOnesieAddToCartBtnDisplayed() {
        return onesieCartBtn.isDisplayed();
    }

    public boolean isRedTShirtAddToCartBtnDisplayed() {
        return  redTShirtCartBtn.isDisplayed();
    }

    public void clickAddBackpackToCart() {
        backpackCartBtn.click();
    }

    public void clickAddBikeLightToCart() {
        bikeLightCartBtn.click();
    }

    public void clickAddBoltTShirtToCart() {
        boltTShirtCartBtn.click();
    }

    public void clickAddFleeceJacketToCart() {
        fleeceJacketCartBtn.click();
    }

    public void clickAddOnesieToCart() {
        onesieCartBtn.click();
    }

    public void clickAddRedTShirtToCart() {
        redTShirtCartBtn.click();
    }

    public boolean isShoppingCartButtonDisplayed() {
        return shoppingCartButton.isDisplayed();
    }

    public boolean isShoppingCartButtonClickable() {
        return shoppingCartButton.isEnabled();
    }

    public void clickShoppingCartButton() {
        shoppingCartButton.click();
    }

    public void clickSidebarElement() {
        sideBar.click();
    }

    public void selectLogOutOptionFromSideBar() {
        wait.until(ExpectedConditions.visibilityOf(logout));
        logout.click();
    }

    public void selectOptionFromProductSorter(String option) {
        Select dropdown = new Select(productSortingDropDown);
        dropdown.selectByVisibleText(option);
    }

    public void selectAboutOptionFromSideBar() {
        wait.until(ExpectedConditions.visibilityOf(about));
        about.click();
    }

    public boolean isProductSortingVisible() {
        return productSortingDropDown.isDisplayed();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(By.className("inventory_item"));
    }

    public boolean isSortingOptionCorrect(String expectedOption) {
        Select dropdown = new Select(productSortingDropDown);
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String actualOption = selectedOption.getText();
        return actualOption.equals(expectedOption);
    }

    public boolean areProductsDisplayed(List<WebElement> itemsBeforeSorting) {
        for (WebElement item : itemsBeforeSorting) {
            if (item.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<Double> getPrices(List<WebElement> itemsList) {
        // This pattern is usually used for finding decimal numbers
        String pattern = "\\d+\\.\\d+";
        List<Double> prices = new ArrayList<>();
        Pattern pricePattern = Pattern.compile(pattern);

        for (WebElement Element : itemsList) {
            String elementText = Element.getText();
            Matcher matcher = pricePattern.matcher(elementText);
            if (matcher.find()) {
                String priceText = matcher.group();
                double price = Double.parseDouble(priceText);
                prices.add(price);
            }
        }
        return prices;
    }

    public boolean areProductsSortedByPriceLowToHigh(List<Double> afterSorting) {
        if (afterSorting == null || afterSorting.isEmpty()) {
            return false;
        }
        for (int i = 0; i < afterSorting.size() - 1; i++) {
            if (afterSorting.get(i) > afterSorting.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductsSortedByPriceHighToLow(List<Double> afterSorting) {
        if (afterSorting == null || afterSorting.isEmpty()) {
            return false;
        }
        for (int i = 0; i < afterSorting.size() - 1; i++) {
            if (afterSorting.get(i) < afterSorting.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductsSortedByName(List<WebElement> beforeSorting, List<WebElement> afterSorting) {
        for (int i = 0; i < beforeSorting.size(); i++) {
            if (beforeSorting.get(i).getText().equals(afterSorting.get(i).getText())) {
                return false;
            }
        }
        return true;
    }
}
