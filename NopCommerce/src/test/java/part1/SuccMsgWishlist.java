package part1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class SuccMsgWishlist {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void addHTCToWishlist() {
        SoftAssert softAssert = new SoftAssert();
        boolean productFound = false;

        // Step 1–2: Get all product blocks from homepage
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));

        for (WebElement product : products) {
            String productName = product.findElement(By.cssSelector("h2.product-title")).getText().trim();

            if (productName.toLowerCase().contains("htc")) {
                productFound = true;

                // Step 3: Click the wishlist button
                WebElement wishlistButton = product.findElement(By.cssSelector("button[title='Add to wishlist']"));
                wishlistButton.click();

                // Step 4: Wait for the success message
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement successMessage = wait.until(d -> d.findElement(By.cssSelector(".bar-notification.success")));

                // Step 5a: Assert message is displayed
                softAssert.assertTrue(successMessage.isDisplayed(), "❌ Success message is not displayed");

                // Step 5b: Assert message text
                String messageText = successMessage.getText().trim();
                softAssert.assertTrue(messageText.contains("The product has been added to your wishlist"),
                        "❌ Success message text incorrect: " + messageText);

                // Step 5c: Assert background color is green
                String rgba = successMessage.getCssValue("background-color");
                String hexColor = rgbaToHex(rgba);
                System.out.println("✅ Success message background color (HEX): " + hexColor);
                softAssert.assertEquals(hexColor, "#4bb07a", "❌ Background color is not green");

                break; // Stop after finding the HTC product
            }
        }

        softAssert.assertTrue(productFound, "❌ HTC smartphone product not found on homepage");
        softAssert.assertAll();
    }



    // Utility: Convert RGBA color to HEX
    private String rgbaToHex(String rgba) {
        String[] parts = rgba.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(parts[0].trim());
        int g = Integer.parseInt(parts[1].trim());
        int b = Integer.parseInt(parts[2].trim());
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
