package Test;

import Common.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class TC09 extends BaseTest {
    @Test
    public void TC07_verifyUserCanCommentAfterLogin() throws InterruptedException {
        driver.get(Constants.URL);

        // Lấy danh sách sản phẩm và chọn ngẫu nhiên 1 sản phẩm
        List<WebElement> allProducts = detailPage.getAllProducts();
        WebElement randomProduct = allProducts.get(new Random().nextInt(allProducts.size()));
        detailPage.clickProduct(randomProduct);

        // Click vào ô comment và link đăng nhập
        detailPage.clickCommentBox();
        detailPage.clickLoginLink();

        // Đăng nhập

        loginPage.login(Constants.EMAIL, Constants.PASSWORD);;

        // Nhập nội dung bình luận
        String comment = "Bình luận tự động hihiuioluiouopio";
        detailPage.enterComment(comment);

        // Gửi bình luận
        detailPage.clickCommentButton();


        // Kiểm tra thông báo và nội dung bình luận mới nhất
        String successMsg = detailPage.getSuccessCommentMessage();
        assertTrue(successMsg.contains("Bình luận thành công"), "Phải hiển thị thông báo thành công");

    }
}
