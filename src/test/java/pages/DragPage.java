package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragPage extends BasePage {
    private final By draggableBox = By.id("sample-box");

    public DragPage(WebDriver driver) {
        super(driver);
    }

    public Rectangle getBoxRectangle() {
        WebElement box = waitAndReturnElement(draggableBox);
        return box.getRect();
    }

    public void performDragAndDrop(int moveX, int moveY) {
        WebElement box = waitAndReturnElement(draggableBox);

        new Actions(driver)
            .clickAndHold(box)
            .moveByOffset(5, 5)
            .dragAndDropBy(box, moveX, moveY)
            .perform();
    }
}
