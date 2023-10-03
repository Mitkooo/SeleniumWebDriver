package Tests.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BaseTests {
    protected WebDriver driver;

    public void initializeDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dimitar.dinkov\\IdeaProjects\\SeleniumStuff\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
            driver.quit();
    }
}
