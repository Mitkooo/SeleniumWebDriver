package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopCartPO extends BasePage {

    @FindBy (xpath = "//*[@class='btn btn_secondary back btn_medium']")
    private WebElement continueShoppingButton;
    @FindBy (xpath = "//*[@class='btn btn_secondary btn_small cart_button']")
    private WebElement removeButton;
    @FindBy (xpath = "//*[@class='shopping_cart_badge']")
    private WebElement shoppingCartBadge;


    public ShopCartPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductNameCorrect(String productName) {
        // Get all products in one list and get their names
        List<WebElement> products = driver.findElements(By.className("cart_list"));
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean areAddedProductsNumberCorrect() {
        // Check if the list of items' size equals to the number from the shop cart badge
        List<WebElement> products = driver.findElements(By.className("cart_list"));
        String badgeString = shoppingCartBadge.getText();
        int badgeNumber = Integer.parseInt(badgeString);
        return products.size() == badgeNumber;
    }
}
