package Tests.Base;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTests {
    protected WebDriver driver;

    public void initializeDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dimitar.dinkov\\IdeaProjects\\SeleniumStuff\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
            driver.quit();
    }
}
