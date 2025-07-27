package Test;

import Common.BaseTest;
import org.testng.annotations.Test;
import utils.Constants;

public class TC38 extends BaseTest {
    @Test
    public void TC10_Verifyuserscanregisternewaccount() throws InterruptedException{
        driver.get(Constants.URL);

        homePage.OpenLoginForm();
        loginPage.OpenPopUpRegisterNewAcc();
        registerPage.RegisterNewAccount(
                "Nguyễn Văn Teo",
                "nguyenvanteohaanh2004@gmail.com",
                "0905129900",
                "123456712",
                "123456712"
        );

        softAssert.assertTrue(registerPage.GetMessageElement().isDisplayed(), "Message is not displayed!");

        registerPage.waitForRegisterSuccessMessageToDisappear();

        homePage.OpenLoginForm();
        loginPage.login("nguyenvanteohaanh2004@gmail.com", "123456712");

        String lastName = homePage.CheckLastNameAtAvatar();
        softAssert.assertTrue(lastName.contains("Teo"), "Last name should be displayed after avatar");
        softAssert.assertAll();
    }
}
