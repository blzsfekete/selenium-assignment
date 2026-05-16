package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import pages.HomePage;
import pages.InputPage;


public class LetCodeTests extends BaseTest {
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
    public void testFillInputFields() {
        HomePage homePage = new HomePage(driver);
        InputPage inputPage = new InputPage(driver);

        homePage.openHomePage();
        driver.get(utils.ConfigReader.getProperty("baseUrl") + "edit");

        inputPage.enterFullName("John Doe");
        inputPage.appendTextAndTab("!!! :)");
        
        String actualText = inputPage.getTextFromGetMeBox();
        assertEquals("ortonikc", actualText);

        inputPage.clearTextBox();
        assertEquals("", inputPage.getClearMeInputValue());
        
        assertTrue(inputPage.isEditFieldDisabled());

        assertTrue(inputPage.isDontWriteFieldReadonly());
    }
}
