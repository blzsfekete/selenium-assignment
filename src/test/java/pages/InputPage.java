package pages;

import org.openqa.selenium.*;

public class InputPage extends BasePage {
    
    private final By fullNameInput = By.id("fullName");
    private final By appendInput = By.id("join");
    private final By getMeInput = By.id("getMe");
    private final By clearMeInput = By.id("clearMe");
    private final By disabledInput = By.id("noEdit");
    private final By readonlyInput = By.id("dontwrite");

    public InputPage(WebDriver driver) {
        super(driver);
    }

    public void enterFullName(String name) {
        WebElement element = waitAndReturnElement(fullNameInput);
        element.sendKeys(name);
    }

    public void appendTextAndTab(String textToAppend) {
        WebElement element = waitAndReturnElement(appendInput);
        element.sendKeys(textToAppend, Keys.TAB);
    }

    public String getTextFromGetMeBox() {
        WebElement element = waitAndReturnElement(getMeInput);
        return element.getAttribute("value"); 
    }

    public void clearTextBox() {
        WebElement element = waitAndReturnElement(clearMeInput);
        element.clear();
    }

    public String getClearMeInputValue() {
        WebElement element = waitAndReturnElement(clearMeInput);
        return element.getAttribute("value");
    }

    public boolean isEditFieldDisabled() {
        WebElement element = waitAndReturnElement(disabledInput);
        return !element.isEnabled(); 
    }

    public boolean isDontWriteFieldReadonly() {
        WebElement element = waitAndReturnElement(readonlyInput);
        String readonlyAttribute = element.getAttribute("readonly");
        return readonlyAttribute != null && readonlyAttribute.equals("true");
    }
}
