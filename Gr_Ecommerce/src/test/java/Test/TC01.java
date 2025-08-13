package Test;

import Common.BaseTest;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

public class TC01 extends BaseTest {

    @Issue("F001")
    @Test
    @Description("Verify that the user can purchase a product successfully when all information fields are valid")
    public void VerifySuccessfulPurchase() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert(); // Khởi tạo soft assert

        driver.get(Constants.URL);
        homePage.OpenLoginForm();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        // random
        homePage.clickButtonAddToCartRandom();
        cartPage.ClickOrderButton();

        // Đổi sang OrderPage để nhập password
        orderPage.EnterPassword(Constants.PASSWORD);
        orderPage.clickPayMentButton();

        // Lấy tên user từ fullname
        String username = orderPage.getFullName();

        // Lấy text cảm ơn
        String thankYouText = paymentPage.getThankYouText();
        String expectedThankYou = "Cảm ơn bạn " + username + " đã tin dùng sản phẩm của chúng tôi";
        softAssert.assertEquals(thankYouText, expectedThankYou, "Thông báo cảm ơn không khớp!");

        // Lấy text liên hệ
        String contactText = paymentPage.getContactText();
        String expectedContact = "Chúng tôi sẽ liên hệ với bạn trong vòng 5 phút. Và giao hàng trong 30 phút.";
        softAssert.assertEquals(contactText, expectedContact, "Thông báo liên hệ không khớp!");

        // Tổng hợp kết quả
        softAssert.assertAll();
    }
}
