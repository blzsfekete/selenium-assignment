package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final By loginHeaderLink = By.id("login_link_top");
    private final By emailInput = By.id("Bugzilla_login_top");
    private final By passwordInput = By.id("Bugzilla_password_top");
    private final By loginSubmitButton = By.id("log_in_top");
    private final By logoutLink = By.linkText("Log out");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void performLogin(String email, String password) {
        waitAndReturnElement(loginHeaderLink).click();
        
        WebElement emailField = waitAndReturnElement(emailInput);
        emailField.sendKeys(email);

        WebElement passField = waitAndReturnElement(passwordInput);
        passField.sendKeys(password);

        waitAndReturnElement(loginSubmitButton).click();
    }

    public void performLogout() {
        waitAndReturnElement(logoutLink).click();
    }

    public boolean isLogoutLinkVisible() {
        return waitAndReturnElement(logoutLink).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return waitAndReturnElement(loginHeaderLink).isDisplayed();
    }
}
