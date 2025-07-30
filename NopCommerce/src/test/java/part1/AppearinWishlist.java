package part1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AppearinWishlist {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void validateWishlistQuantity() {
        // Step 1: Locate the HTC smartphone on homepage
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        WebElement htcProduct = null;

        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector("h2.product-title")).getText().toLowerCase();
            if (name.contains("htc")) {
                htcProduct = product;
                break;
            }
        }

        Assert.assertNotNull(htcProduct, " HTC product not found on homepage!");

        // Step 2: Click wishlist button for HTC product
        WebElement wishlistButton = htcProduct.findElement(By.cssSelector("button[title='Add to wishlist']"));
        wishlistButton.click();

        // Step 3: Wait for success message to appear
        WebElement successBar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.bar-notification.success")));

        // Step 4: Wait for success message to disappear
        wait.until(ExpectedConditions.invisibilityOf(successBar));

        // Step 5: Click on Wishlist link in header
        WebElement wishlistLink = driver.findElement(By.cssSelector("a[href='/wishlist']"));
        wishlistLink.click();

        // Step 6: On Wishlist page, get quantity input value
        WebElement qtyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.qty-input")));

        String qtyValue = qtyInput.getAttribute("value"); // e.g., "1"
        System.out.println(" Quantity value: " + qtyValue);

        int qty = Integer.parseInt(qtyValue);
        Assert.assertTrue(qty > 0, " Quantity is not greater than 0");
        System.out.println(" Wishlist quantity assertion passed.");
    }

   
}
