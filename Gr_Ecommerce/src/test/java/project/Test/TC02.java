package project.Test;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import project.Constraint.ConfigData;
import project.Page.User.SearchPage;

import java.time.Duration;
import java.util.List;

public class TC02 {
    @Description("Verify that users can search for a product by keyword, confirm the product matches the keyword and can view its detail page")
    @Test
    public void verifyUserCanSearchProductByKeyword() {
        String keyword = "samsung";
        List<String> matchedProducts =searchPage.searchProductsByKeyword(keyword);
        searchPage.SelectProduct();

        softAssert.assertTrue(matchedProducts.size() > 0, "No product contains the keyword: " + keyword);

        for (String name : matchedProducts) {
            softAssert.assertTrue(name.toLowerCase().contains(keyword.toLowerCase()), "Product name does not match keyword: " + name);
        }
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("product_details"), "Detail page URL is invalid: " + currentUrl);

        softAssert.assertAll();
    }

    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;
    SearchPage searchPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigData.URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
