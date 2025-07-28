package Test;

import Common.BaseTest;
import org.testng.annotations.Test;
import utils.Constants;

public class TC11 extends BaseTest {
    @Test
    public void TC08_verifyUserCanLoadMoreComment() throws InterruptedException {
        driver.get(Constants.URL);
        detailPage.clickProduct(detailPage.getRandomProduct());

        detailPage.verifyCommentLoadMoreFunctionality();
    }

}
