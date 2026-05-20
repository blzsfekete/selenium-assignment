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
        WebElement fullNameElement = waitAndReturnElement(fullNameInput);
        fullNameElement.sendKeys(name);
    }

    public String getFullNameValue() {
        WebElement fullNameElement = waitAndReturnElement(fullNameInput);
        return fullNameElement.getAttribute("value");
    }

    public void appendTextAndTab(String textToAppend) {
        WebElement appendElement = waitAndReturnElement(appendInput);
        appendElement.sendKeys(textToAppend, Keys.TAB);
    }

    public String getTextFromGetMeBox() {
        WebElement getMeElement = waitAndReturnElement(getMeInput);
        return getMeElement.getAttribute("value"); 
    }

    public void clearTextBox() {
        WebElement clearMeElement = waitAndReturnElement(clearMeInput);
        clearMeElement.clear();
    }

    public String getClearMeInputValue() {
        WebElement clearMeElement = waitAndReturnElement(clearMeInput);
        return clearMeElement.getAttribute("value");
    }

    public boolean isEditFieldDisabled() {
        WebElement disabledElement = waitAndReturnElement(disabledInput);
        return !disabledElement.isEnabled(); 
    }

    public boolean isDontWriteFieldReadonly() {
        WebElement readonlyElement = waitAndReturnElement(readonlyInput);
        String readonlyAttribute = readonlyElement.getAttribute("readonly");
        return readonlyAttribute != null && readonlyAttribute.equals("true");
    }
}
