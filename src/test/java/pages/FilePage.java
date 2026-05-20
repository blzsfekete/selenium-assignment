package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FilePage extends BasePage {
    private final By fileInput = By.xpath("//input[@type='file']");
    private final By selectedFileText = By.xpath("//app-file//p");
    private final By downloadTextButton = By.id("txt");

    public FilePage(WebDriver driver) {
        super(driver);
    }

    public void uploadFile(String filePath) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        LocalFileDetector detector = new LocalFileDetector();
        ((RemoteWebElement)input).setFileDetector(detector);

        File file = detector.getLocalFile(filePath);
        
        input.sendKeys(file.getAbsolutePath());
    }

    public String getSelectedFileText() {
        WebElement textElement = waitAndReturnElement(selectedFileText);
        return textElement.getText();
    }

    public void clickDownloadTextFile() {
        WebElement btn = waitAndReturnElement(downloadTextButton);
        btn.click();
    }

    public String getDownloadedFileNameFromBrowser() throws InterruptedException {
        Thread.sleep(2000);

        driver.navigate().to("chrome://downloads/");

        Thread.sleep(1000);

        JavascriptExecutor je = (JavascriptExecutor) driver;
        String shadowDomQuery = 
            "return document.querySelector('downloads-manager').shadowRoot" +
            ".querySelector('#downloadsList downloads-item').shadowRoot" +
            ".querySelector('#fileLink').textContent;";

        String fileName = (String) je.executeScript(shadowDomQuery);

        driver.navigate().back();

        return fileName;
    }
}
