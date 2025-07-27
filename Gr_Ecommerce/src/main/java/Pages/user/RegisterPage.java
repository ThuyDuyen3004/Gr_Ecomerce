package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    private By FullNameTextLocator=By.id("name_rg");
    private By EmailTextLocator=By.id("email_rg");
    private By PhoneTextLocotor=By.id("phone_rg");
    private By PassWordTextLocotor=By.id("pass_rg");
    private By VerifyPassWordTextLocotor=By.id("c_pass_rg");
    private By RegisButton=By.id("submit_rg");
    private By successMessage = By.xpath("//*[contains(text(),'Đăng ký thành công')]");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void RegisterNewAccount(String f, String e, String p, String pw, String vrpw )
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FullNameTextLocator)).sendKeys(f);
        driver.findElement(EmailTextLocator).sendKeys(e, Keys.TAB);
        driver.findElement(PhoneTextLocotor).sendKeys(p, Keys.TAB);
        driver.findElement(PassWordTextLocotor).sendKeys(pw, Keys.TAB);
        driver.findElement(VerifyPassWordTextLocotor).sendKeys(vrpw, Keys.TAB);
        driver.findElement(RegisButton).click();

    }
    public void waitForRegisterSuccessMessageToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(GetMessageElement()));
    }

    public WebElement GetMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
    }


}
