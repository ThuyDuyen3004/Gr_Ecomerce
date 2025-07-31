package Test;

import Common.BaseTest;
import org.testng.annotations.Test;
import utils.Constants;

public class TC17 extends BaseTest {
    @Test
    public void UserCanLoginSuccessfullyWithValidAccount() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.USERNAME_ADMIN, Constants.PASSWORD_ADMIN);
        String lastName = homePage.CheckLastNameAtAvatar();
        softAssert.assertTrue(lastName.contains("DUYÊN"), "Last name should be displayed after avatar");
        softAssert.assertAll();
    }
}
