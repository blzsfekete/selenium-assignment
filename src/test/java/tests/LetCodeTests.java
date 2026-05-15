package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import pages.HomePage;


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
}
