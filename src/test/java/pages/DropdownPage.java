package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.lang.Thread;

public class DropdownPage extends BasePage {
    private final By fruitDropdown = By.xpath("//label[contains(text(), 'Select the apple')]/following-sibling::div//select");
    private final By languageDropdown = By.xpath("//select[@id='lang' and descendant::option[text()='Java']]");
    private final By countryDropdown = By.xpath("//label[contains(text(), 'Select India')]/following::select[@id='country']");
    private final By selectedFruit = By.xpath("//p[contains(text(), 'You have selected')]");
    private final By selectedHeroAndLanguage = By.xpath("(//p[contains(@class, 'subtitle')])[2]");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public void selectApple() {
        WebElement fruitDropdownElement = waitAndReturnElement(fruitDropdown);
        
        Select select = new Select(fruitDropdownElement);
        select.selectByVisibleText("Apple");
    }

    public void selectHeroesUsingJS(String heroName) throws InterruptedException {
        String xpathHeroString = String.format("//select[@id='superheros']//option[text()='%s']", heroName);

        WebElement heroElement = waitAndReturnElement(By.xpath(xpathHeroString));

        JavascriptExecutor je = (JavascriptExecutor) driver;

        je.executeScript("arguments[0].scrollIntoView(true);", heroElement);
        heroElement.click();
        Thread.sleep(1000);
    }

    public void selectLanguage(String language) {
        WebElement languageDropdownElement = waitAndReturnElement(languageDropdown);

        Select select = new Select(languageDropdownElement);
        select.selectByVisibleText(language);
    }

    public void selectCountryByValue(String countryCode) {
        WebElement countryDropdownElement = waitAndReturnElement(countryDropdown);

        Select select = new Select(countryDropdownElement);
        select.selectByValue(countryCode);
    }

    public String getSelectedFruitText() {
        WebElement element = waitAndReturnElement(selectedFruit);
        return element.getText();
    }

    public String getSelectedHeroAndLanguageText() {
        WebElement element = waitAndReturnElement(selectedHeroAndLanguage);
        return element.getText();
    }

    public String getSelectedCountryValue() {
        WebElement element = waitAndReturnElement(countryDropdown);
        Select select = new Select(element);
        
        return select.getFirstSelectedOption().getAttribute("value");
    }
}
