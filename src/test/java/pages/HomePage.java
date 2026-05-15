package pages;

import org.openqa.selenium.*;
import utils.ConfigReader;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(ConfigReader.getProperty("baseUrl")); 
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public void navigateToWorkspace() {
        driver.get(ConfigReader.getProperty("baseUrl") + "test");
    }
}
