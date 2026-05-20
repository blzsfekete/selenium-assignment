package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pages.LoginPage;
import pages.MyRequestsPage;
import utils.ConfigReader;

public class BugzillaTests extends BaseTest {
    // Testing user lifecycle features on a different page
    @Test
    public void testCompleteUserLifecycle() {
        LoginPage loginPage = new LoginPage(driver);
        MyRequestsPage requestsPage = new MyRequestsPage(driver);

        driver.get("https://bugzilla52.allizgub.org/");

        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");
        
        loginPage.performLogin(email, password);
        assertTrue(loginPage.isLogoutLinkVisible());

        requestsPage.navigateToMyRequests();

        requestsPage.clearRequesterEmail();
        requestsPage.clearRequesteeEmail();

        requestsPage.submitFilter();
        assertEquals("testing.syinfo@servilinksystems.com", requestsPage.getFirstTableResult());

        loginPage.performLogout();
        assertTrue(loginPage.isLoginButtonVisible());
    }
}
