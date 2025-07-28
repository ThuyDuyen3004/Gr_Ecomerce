package Test;

import Common.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import utils.Constants;

import java.util.Locale;

import static org.testng.Assert.assertTrue;

public class TC09 extends BaseTest {
    @Test
    public void TC07_verifyUserCanCommentAfterLogin() throws InterruptedException {
        driver.get(Constants.URL);

        detailPage.clickProduct(detailPage.getRandomProduct());

        detailPage.clickCommentBox();
        detailPage.clickLoginLink();

        loginPage.login(Constants.EMAIL, Constants.PASSWORD);

        Faker faker = new Faker(new Locale("en"));
        String comment = faker.lorem().sentence(10);

        detailPage.enterComment(comment);

        detailPage.clickCommentButton();

        assertTrue( detailPage.getSuccessCommentMessage().contains("Bình luận thành công."), "Success message should be displayed");
    }
}
