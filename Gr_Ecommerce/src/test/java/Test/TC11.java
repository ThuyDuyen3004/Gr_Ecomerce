package Test;

import Common.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class TC11 extends BaseTest {
    @Test
    public void TC08_verifyUserCanLoadMoreComment() throws InterruptedException {
        driver.get(Constants.URL);

        // Lấy danh sách tất cả sản phẩm và click ngẫu nhiên
        List<WebElement> allProducts = detailPage.getAllProducts();
        WebElement randomProduct = allProducts.get(new Random().nextInt(allProducts.size()));
        detailPage.clickProduct(randomProduct);

        // Lấy số lượng bình luận ban đầu
        int initialCount = detailPage.getNumberOfComments();
        System.out.println("Số bình luận ban đầu: " + initialCount);


        detailPage.clickLoadMore();

        // Lấy số lượng bình luận mới
        int newCount = detailPage.getNumberOfComments();
        System.out.println("Số bình luận sau khi tải thêm: " + newCount);
        assertTrue(newCount > initialCount, "Phải có thêm bình luận sau khi click 'Xem thêm'");
    }
}
