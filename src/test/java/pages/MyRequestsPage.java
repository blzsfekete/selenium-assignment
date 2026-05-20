package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyRequestsPage extends BasePage {
    private final By myRequestsLink = By.linkText("My Requests"); 
    private final By requesterInput = By.id("requester");
    private final By requesteeInput = By.id("requestee");
    private final By filterButton = By.id("filter");
    private final By firstTableResult = By.xpath("//table[@class='requests']//tr[2]/td[1]"); 

    public MyRequestsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToMyRequests() {
        waitAndReturnElement(myRequestsLink).click();
    }

    public void clearRequesterEmail() {
        WebElement input = waitAndReturnElement(requesterInput);
        input.clear();
    }

    public void clearRequesteeEmail() {
        WebElement input = waitAndReturnElement(requesteeInput);
        input.clear();
    }

    public void submitFilter() {
        waitAndReturnElement(filterButton).click();
    }

    public String getFirstTableResult() {
        return waitAndReturnElement(firstTableResult).getText();
    }
}
