package Test;

import Common.BaseTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.Locale;

public class TC37 extends BaseTest {
    @Test
    public void TC10_Verifyuserscanregisternewaccount() throws InterruptedException {
        driver.get(Constants.URL);

        homePage.OpenLoginForm();
        loginPage.OpenPopUpRegisterNewAcc();

        Faker faker = new Faker(new Locale("vi"));

        String fullName = faker.name().fullName();
        String email = "user" + System.currentTimeMillis() + "@gmail.com"; // đảm bảo email là duy nhất
        String phone = "0" + faker.number().digits(9);
        String password = faker.number().digits(8);

        registerPage.RegisterNewAccount(
                fullName,
                email,
                phone,
                password,
                password
        );

        softAssert.assertTrue(registerPage.GetMessageElement().isDisplayed(), "Message is not displayed!");

        registerPage.waitForRegisterSuccessMessageToDisappear();

        homePage.OpenLoginForm();
        loginPage.login(email, password);

        softAssert.assertTrue(homePage.CheckLastNameAtAvatar().contains(fullName.split(" ")[fullName.split(" ").length - 1]),
                "Last name should be displayed after avatar");
        softAssert.assertAll();
    }

}
