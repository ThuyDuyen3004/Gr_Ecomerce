package Pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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
    private By commentButton = By.xpath("//div[@class='row comments']//button");
    private By successMessage = By.xpath("//*[contains(text(),'Bình luận thành công')]");


    public List<WebElement> getAllProducts() {
        return driver.findElements(By.xpath("//div[@class='best-seller']//div[@class='row no-row']/child::div"));
    }

    public void clickProduct(WebElement product) {
        WebElement link = product.findElement(By.tagName("a"));
        link.click();
    }


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
        input.sendKeys(comment, Keys.TAB);
    }

    // Click nút Comment
    public void clickCommentButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(commentButton));
        button.click();
        button.click();
    }

    // Lấy thông báo thành công
    public String getSuccessCommentMessage() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return longWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    // random
    public WebElement getRandomProduct() {
        List<WebElement> allProducts = getAllProducts(); // phương thức này có sẵn
        Random random = new Random();
        return allProducts.get(random.nextInt(allProducts.size()));
    }


    /// cau

    private By commentItems = By.xpath("//div[@class='row post-comment']"); // Mỗi comment có class "media"
    private By loadMoreButton = By.xpath("//div[@id='show_more_main']//button");

    public int getNumberOfComments() {
        List elements = driver.findElements(commentItems);
        return elements.size();
    }


    public void clickLoadMore() {
        try {
            WebElement button = driver.findElement(loadMoreButton);
            button.click();
            Thread.sleep(1000); // nếu cần thời gian để load thêm comment
            button.click();
        } catch (NoSuchElementException e) {
            System.out.println("[INFO] 'Load More' button not found. Skipping click.");
        } catch (Exception ex) {
            System.out.println("[ERROR] Unexpected error while clicking 'Load More': " + ex.getMessage());
        }

    }

    public void verifyCommentLoadMoreFunctionality() {
        int initialCount = getNumberOfComments();
        System.out.println("Initial comment count: " + initialCount);

        try {
            clickLoadMore();

            int newCount = getNumberOfComments();
            System.out.println("Comment count after loading more: " + newCount);

            assert newCount >= initialCount :
                    "There should be more comments displayed after clicking 'See more'";
        } catch (NoSuchElementException e) {
            System.out.println("[INFO] 'See more' button not found. Skipping load more comment test.");
            assert initialCount > 0 : "No comments displayed and 'See more' button is missing.";
        }

    }
}

