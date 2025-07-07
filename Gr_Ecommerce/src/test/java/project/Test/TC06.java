package project.Test;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import project.Constraint.ConfigData;
import project.Page.CartPage;
import project.Page.HomePage;
import project.Page.LoginPage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TC06 {
    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    @Description("Verify that [Thành tiền] and [Tổng tiền] are calculated correctly for all products in the cart")
    public void VerifythatCalculatedCorrectly() throws InterruptedException {
        driver.get(ConfigData.URL);
        homePage.OpenLoginForm();
        loginPage.login(ConfigData.EMAIL, ConfigData.PASSWORD);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            List<WebElement> allButtons = homePage.getAllAddToCartButtons();


            WebElement randomButton = allButtons.get(random.nextInt(allButtons.size()));
            wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
            cartPage.CloseCartForm();
        }

        homePage.clickCartIcon();
        cartPage.ClickOrderButton();
        homePage.clickCartIcon();
        Thread.sleep(1000);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
