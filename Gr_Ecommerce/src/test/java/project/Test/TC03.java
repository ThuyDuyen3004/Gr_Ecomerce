package project.Test;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import project.Constraint.ConfigData;
import project.Page.User.CartPage;
import project.Page.User.HomePage;
import project.Page.User.LoginPage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TC03 {
    @Test
    @Description("Verify that the products in the cart remain intact and are  not lost or changed when the  user exits the account")
    public void verifyCartPersistenceAfterLogout() throws InterruptedException{
        driver.get(ConfigData.URL);
        homePage.OpenLoginForm();
        loginpage.login(ConfigData.EMAIL, ConfigData.PASSWORD);
        // random
        List<WebElement> allButtons = homePage.getAllAddToCartButtons();
        WebElement randomButton = allButtons.get(new Random().nextInt(allButtons.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomButton)).click();
        cartPage.CloseCartForm();
        homePage.clickUserName();
        homePage.logoutButton();
        homePage.OpenLoginForm();
        loginpage.login(ConfigData.EMAIL, ConfigData.PASSWORD);
        softAssert.assertTrue(homePage.QuatityInCart() == 1, "Expected quantity in cart = 1 but got: " + homePage.QuatityInCart());
        softAssert.assertAll();
    }
    WebDriver driver;
    SoftAssert softAssert;
    HomePage homePage;
    LoginPage loginpage;
    CartPage cartPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
