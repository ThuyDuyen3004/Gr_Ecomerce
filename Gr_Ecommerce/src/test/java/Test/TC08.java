package Test;

import Common.BaseTest;
import org.testng.annotations.Test;
import utils.Constants;

public class TC08 extends BaseTest {
    @Test
    public void VerifyThatTheButtonIsDisableWhenNoProductInCart() {
        driver.get(Constants.URL);
        homePage.clickCartIcon();
        boolean isEnabled = homePage.isDatHangNgayButtonDisabled();
        String ErrorMessage=homePage.GetErrorMessage();
        Integer QuatityInCart=homePage.QuatityInCart();
        softAssert.assertTrue(isEnabled, "'Đặt hàng ngay' button should be disabled when cart is empty");
        softAssert.assertEquals(ErrorMessage, "Vui lòng chọn sản phẩm", "The message does not match");
        softAssert.assertEquals(QuatityInCart.intValue(), 0, "The quantity in cart should be 0");

        softAssert.assertAll();
    }
}
