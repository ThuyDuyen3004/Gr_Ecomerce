package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class detailPage {
    WebDriver driver;
    WebDriverWait wait;

    public detailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By commentBox = By.id("comment");
    private By loginPrompt = By.xpath("//p[contains(@class, 'alert-danger')]");
    private By loginLink = By.xpath("//a[contains(text(),'Click vào đây để đăng nhập')]");
    private By commentInput = By.id("comment");
    private By commentButton = By.xpath("(//div[@class='row comments']/descendant::button)");
    private By successMessage = By.xpath("//*[contains(text(),'Bình luận thành công')]");


    public List<WebElement> getAllProducts() {
         return driver.findElements(By.xpath("//div[@class='best-seller']//div[@class='row no-row']/child::div"));
    }
    public void clickProduct(WebElement product) {
    WebElement link = product.findElement(By.tagName("a"));
    link.click();}



    // Click vào ô comment
    public void clickCommentBox() {
        wait.until(ExpectedConditions.elementToBeClickable(commentBox)).click();
    }

    // Lấy message yêu cầu đăng nhập
    public String getLoginPromptMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPrompt)).getText();
    }

    // Click vào link đăng nhập
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // Nhập nội dung bình luận
    public void enterComment(String comment) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(commentInput));
        input.clear();
        input.click();
        input.sendKeys(comment,Keys.TAB);
    }

    // Click nút Comment
    public void clickCommentButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(commentButton));
        button.click();
    }

    // Lấy thông báo thành công
    public String getSuccessCommentMessage() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return longWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }


///cau

    private By commentItems = By.xpath("//div[@class='row post-comment']"); // Mỗi comment có class "media"
    private By loadMoreButton = By.xpath("//div[@id='show_more_main']/button");

    public int getNumberOfComments() {
        List elements = driver.findElements(commentItems);
        return elements.size();
    }

    public boolean isLoadMoreVisible() {
        return driver.findElements(loadMoreButton).size() > 0;
    }

    public void clickLoadMore() {
        driver.findElement(loadMoreButton).click();
    }
}
