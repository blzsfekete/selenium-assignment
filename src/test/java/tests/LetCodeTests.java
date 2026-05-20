package tests;

import com.github.javafaker.Faker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;
import org.openqa.selenium.Rectangle;

import pages.DragPage;
import pages.DropdownPage;
import pages.FilePage;
import pages.HomePage;
import pages.InputPage;
import pages.RadioPage;

public class LetCodeTests extends BaseTest {
    private static final String UPLOAD_FILE_PATH = "src/test/resources/test-upload.txt";

    @Test
    public void testHomePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(driver);
        
        homePage.openHomePage();
        String title = homePage.getPageTitle();
        
        assertEquals("LetCode with Koushik", title);
    }

    @Test
    public void testNavigationToWorkspace() {
        HomePage homePage = new HomePage(driver);
        
        homePage.navigateToWorkspace();
        
        assertTrue(driver.getCurrentUrl().contains("/test"));
    }

    @Test
    public void testWorkspacePagesLoadSuccessfully() {
        String[] endpoints = {"edit", "button", "dropdowns", "radio", "droppable"};

        for (String endpoint : endpoints) {
            navigateTo(endpoint);

            assertTrue(driver.getCurrentUrl().contains(endpoint));

            assertTrue(driver.getTitle().toLowerCase().contains(endpoint));
        }
    }

    @Test
    public void testFillInputFields() {
        InputPage inputPage = new InputPage(driver);
        navigateTo("edit");

        inputPage.enterFullName("John Doe");
        inputPage.appendTextAndTab("!!! :)");
        
        String actualText = inputPage.getTextFromGetMeBox();
        assertEquals("ortonikc", actualText);

        inputPage.clearTextBox();
        assertEquals("", inputPage.getClearMeInputValue());
        
        assertTrue(inputPage.isEditFieldDisabled());

        assertTrue(inputPage.isDontWriteFieldReadonly());
    }

    @Test
    public void testRandomDataEntry() {
        InputPage inputPage = new InputPage(driver);
        navigateTo("edit");

        Faker faker = new Faker();

        String randomFullName = faker.name().fullName();

        inputPage.enterFullName(randomFullName);
        assertEquals(randomFullName, inputPage.getFullNameValue());
    }

    @Test
    public void testComplexDropdownInteractions() throws InterruptedException {
        DropdownPage dropdownPage = new DropdownPage(driver);
        navigateTo("dropdowns");

        dropdownPage.selectApple();
        assertTrue(dropdownPage.getSelectedFruitText().contains("Apple"));

        dropdownPage.selectHeroesUsingJS("Daredevil");
        assertTrue(dropdownPage.getSelectedHeroAndLanguageText().contains("Daredevil"));

        dropdownPage.selectLanguage("Java");
        assertTrue(dropdownPage.getSelectedHeroAndLanguageText().contains("Java"));

        dropdownPage.selectCountryByValue("India");
        assertEquals("India", dropdownPage.getSelectedCountryValue());
    }

    @Test
    public void testDragAndDrop() {
        DragPage dragPage = new DragPage(driver);
        navigateTo("draggable");

        Rectangle start = dragPage.getBoxRectangle();

        int moveX = 100;
        int moveY = 50;
        dragPage.performDragAndDrop(moveX, moveY);

        Rectangle finish = dragPage.getBoxRectangle();

        boolean movedX = Math.abs(finish.getX() - (start.getX() + moveX)) <= 1;
        boolean movedY = Math.abs(finish.getY() - (start.getY() + moveY)) <= 1;

        assertTrue(movedX);
        assertTrue(movedY);
    }

    @Test
    public void testFileUpload() {
        FilePage filePage = new FilePage(driver);
        navigateTo("file");

        filePage.uploadFile(UPLOAD_FILE_PATH);
        assertTrue(filePage.getSelectedFileText().contains("test-upload.txt"));
    }

    @Test
    public void testFileDownload() throws InterruptedException {
        FilePage fileDownloadPage = new FilePage(driver);
        navigateTo("file");

        fileDownloadPage.clickDownloadTextFile();
        assertTrue(fileDownloadPage.getDownloadedFileNameFromBrowser().contains(".txt"));
    }

    @Test
    public void testRadioAndCheckboxes() {
        RadioPage radioPage = new RadioPage(driver);
        navigateTo("radio");

        assertTrue(radioPage.isRememberMeChecked());
        assertTrue(radioPage.isMaybeDisabled());

        radioPage.clickYesRadio();
        assertTrue(radioPage.isYesSelected());

        radioPage.clickRememberMeCheckbox();
        assertFalse(radioPage.isRememberMeChecked());
    }
}
