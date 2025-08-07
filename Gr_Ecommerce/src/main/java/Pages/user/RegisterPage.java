package Pages.user;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    protected WebDriverWait wait;
    private By FullNameTextLocator = By.id("name_rg");
    private By EmailTextLocator = By.id("email_rg");
    private By PhoneTextLocotor = By.id("phone_rg");
    private By PassWordTextLocotor = By.id("pass_rg");
    private By VerifyPassWordTextLocotor = By.id("c_pass_rg");
    private By RegisButton = By.id("submit_rg");
    private By successMessage = By.xpath("//*[contains(text(),'Đăng ký thành công')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void RegisterNewAccount(String f, String e, String p, String pw, String vrpw) {
        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(FullNameTextLocator));
        fullNameField.clear();
        fullNameField.sendKeys(f);

        WebElement emailField = driver.findElement(EmailTextLocator);
        emailField.clear();
        emailField.sendKeys(e, Keys.TAB);

        WebElement phoneField = driver.findElement(PhoneTextLocotor);
        phoneField.clear();
        phoneField.sendKeys(p, Keys.TAB);

        WebElement passwordField = driver.findElement(PassWordTextLocotor);
        passwordField.clear();
        passwordField.sendKeys(pw, Keys.TAB);

        WebElement verifyPasswordField = driver.findElement(VerifyPassWordTextLocotor);
        verifyPasswordField.clear();
        verifyPasswordField.sendKeys(vrpw, Keys.TAB);
    }

    public void ClickRegisButton() {
        driver.findElement(RegisButton).click();
    }


    public void waitForRegisterSuccessMessageToDisappear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(successMessage));
        } catch (Exception e) {
            System.out.println("Old message may not have disappeared yet: " + e.getMessage());
        }
    }

    public WebElement GetMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
    }

    public String getWarningMessage() {
        By locator = By.xpath("//i[@class='text-danger']");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception ignored) {
        }

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return messageElement.getText();
        } catch (Exception e) {
            return "MESSAGE NOT FOUND";
        }
    }


}
