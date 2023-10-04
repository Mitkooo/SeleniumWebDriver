package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Constants.HomeConstants.*;

public class HomePO extends BasePage {
    @FindBy (xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement sideBar;
    @FindBy (xpath = "//*[@id='logout_sidebar_link']")
    private WebElement logout;
    @FindBy (xpath = "//*[@id='about_sidebar_link']")
    private WebElement about;
    @FindBy (xpath = "//*[@class='product_sort_container']")
    private WebElement productSortingDropDown;
    @FindBy (xpath = "//*[@class='shopping_cart_link']")
    private WebElement shoppingCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement bikeLightCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement backpackCartBtn;
    @FindBy (xpath = "//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement boltTShirt;
    @FindBy (xpath = "//*[@href='https://twitter.com/saucelabs']")
    private WebElement twitterLink;
    @FindBy (xpath = "//*[@href='https://www.facebook.com/saucelabs']")
    private WebElement facebookLink;
    @FindBy (xpath = "//*[@href='https://www.linkedin.com/company/sauce-labs/']")
    private WebElement linkedInLink;
    @FindBy (xpath = "//*[@class='footer_copy']")
    private WebElement copyrightStatement;

    public HomePO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCopyrightStatementDisplayed() {
        return copyrightStatement.isDisplayed();
    }
    public String getCopyrightStatementText() {
        return copyrightStatement.getText();
    }
    public void clickTwitterLinkIcon() {
        twitterLink.click();
    }
    public boolean isTwitterLinkIconDisplayed() {
        return twitterLink.isDisplayed();
    }
    public void clickFacebookLinkIcon() {
        facebookLink.click();
    }
    public boolean isFacebookLinkIconDisplayed() {
        return facebookLink.isDisplayed();
    }
    public void clickLinkedInLinkIcon() {
        linkedInLink.click();
    }
    public boolean isLinkedInLinkIconDisplayed() {
        return linkedInLink.isDisplayed();
    }
    public boolean isSidebarVisible() {
        return sideBar.isDisplayed();
    }
    public boolean isBoltTShirtAddToCartBtnDisplayed() { return boltTShirt.isDisplayed(); }
    public void clickBoltTShirtAddToCartBtn() { boltTShirt.click(); }
    public boolean isBackpackAddToCartBtnDisplayed() { return backpackCartBtn.isDisplayed(); }
    public void clickBackpackAddToCartBtn() { backpackCartBtn.click(); }
    public boolean isBikeLightAddToCartBtnDisplayed() {
        return bikeLightCartBtn.isDisplayed();
    }
    public void clickAddBikeLightToCart() {
        bikeLightCartBtn.click();
    }
    public boolean isShoppingCartButtonDisplayed() {
        return shoppingCartBtn.isDisplayed();
    }
    public void clickShoppingCartButton() {
        shoppingCartBtn.click();
    }
    public void clickSidebarElement() { sideBar.click(); }

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

    public void switchTab() {
        // Get all window handles
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                return; // Switched to the new tab, exit the loop
            }
        }
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
