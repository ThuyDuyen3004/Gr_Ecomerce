package Test;

import Common.BaseTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.Locale;

public class RegisterNegativeTests extends BaseTest {
    Faker faker = new Faker(new Locale("vi"));

    public final String validFullName = faker.name().fullName();
    public final String shortName = faker.lorem().characters(1, 5);
    public final String numberName = faker.number().digits(6);
    public final String specialCharName = "@#$%^&";

    public final String validEmail = "user" + System.currentTimeMillis() + "@gmail.com";
    public final String invalidEmail = "abc@";
    public final String existedEmail = "leduyenduyen3004123@gmail.com";

    public final String validPhone = "09" + faker.number().digits(8);
    public final String invalidPhone = faker.lorem().characters(6);
    public final String existedPhone = "0376357073";

    public final String validPassword = faker.internet().password(8, 12);
    public final String shortPassword = faker.internet().password(1, 5);
    public final String mismatchPassword = validPassword + "321";

    @Test
    public void VerifyUserCantRegister() throws InterruptedException {
        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.OpenPopUpRegisterNewAcc();

        // Step 3: Họ và tên rỗng
        registerPage.RegisterNewAccount("", validEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Họ và tên không được để trống", "Sai message ở Step 3");

        // Step 5: Họ và tên toàn số
        registerPage.RegisterNewAccount(numberName, validEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Họ và tên không đúng định dạng", "Sai message ở Step 5");

        // Step 6: Họ và tên toàn ký tự đặc biệt
        registerPage.RegisterNewAccount(specialCharName, validEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Họ và tên không đúng định dạng", "Sai message ở Step 6");

        // Step 7: Họ và tên < 6 ký tự
        registerPage.RegisterNewAccount(shortName, validEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Họ và tên phải trên 6 ký tự", "Sai message ở Step 7");

        // Step 10: Email rỗng
        registerPage.RegisterNewAccount(validFullName, "", validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Email không được để trống", "Sai message ở Step 10");

        // Step 11: Email sai định dạng
        registerPage.RegisterNewAccount(validFullName, invalidEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Email không đúng định dạng", "Sai message ở Step 11");

        // Step 12: Email đã tồn tại
        registerPage.RegisterNewAccount(validFullName, existedEmail, validPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Email đã có trong CSDL", "Sai message ở Step 12");

        // Step 14: SĐT rỗng
        registerPage.RegisterNewAccount(validFullName, validEmail, "", validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Số điện thoại không được để trống", "Sai message ở Step 14");

        // Step 15: SĐT sai định dạng
        registerPage.RegisterNewAccount(validFullName, validEmail, invalidPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Số điện thoại không đúng định dạng", "Sai message ở Step 15");

        // Step 16: SĐT đã tồn tại
        registerPage.RegisterNewAccount(validFullName, validEmail, existedPhone, validPassword, validPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Số điện thoại đã có trong CSDL", "Sai message ở Step 16");

        // Step 18: Password rỗng
        registerPage.RegisterNewAccount(validFullName, validEmail, validPhone, "", "");
        softAssert.assertEquals(registerPage.getWarningMessage(), "Mật khẩu không được để trống", "Sai message ở Step 18");

        // Step 19: Password < 6 ký tự
        registerPage.RegisterNewAccount(validFullName, validEmail, validPhone, shortPassword, shortPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Mật khẩu phải trên 6 ký tự", "Sai message ở Step 19");

        // Step 22: Nhập lại mật khẩu rỗng
        registerPage.RegisterNewAccount(validFullName, validEmail, validPhone, validPassword, "");
        softAssert.assertEquals(registerPage.getWarningMessage(), "Nhập lại pass không được để trống", "Sai message ở Step 22");

        // Step 23: Mật khẩu không khớp
        registerPage.RegisterNewAccount(validFullName, validEmail, validPhone, validPassword, mismatchPassword);
        softAssert.assertEquals(registerPage.getWarningMessage(), "Pass không trùng khớp với nhau", "Sai message ở Step 23");

        softAssert.assertAll();
    }

}
