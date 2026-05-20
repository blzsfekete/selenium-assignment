package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioPage extends BasePage {
    private final By yesRadioButton = By.id("yes");
    private final By rememberMeCheckbox = By.xpath("//label[contains(normalize-space(), 'Remember me')]/input");
    private final By disabledRadioButton = By.id("maybe");

    public RadioPage(WebDriver driver) {
        super(driver);
    }

    public void clickYesRadio() {
        WebElement btn = waitAndReturnElement(yesRadioButton);
        btn.click();
    }

    public void clickRememberMeCheckbox() {
        WebElement checkbox = waitAndReturnElement(rememberMeCheckbox);
        checkbox.click();
    }

    public boolean isYesSelected() {
        return waitAndReturnElement(yesRadioButton).isSelected();
    }

    public boolean isRememberMeChecked() {
        return waitAndReturnElement(rememberMeCheckbox).isSelected();
    }

    public boolean isMaybeDisabled() {
        return !waitAndReturnElement(disabledRadioButton).isEnabled();
    }
}
