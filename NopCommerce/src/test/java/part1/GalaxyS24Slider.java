package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class GalaxyS24Slider {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void homeslider2(){
        WebElement img2 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("img[src*='0000080_banner_2.webp']")));

        // Step 2: Click the second slider
        img2.click();


        System.out.println("Clicked first slider - malak");



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.button-1.search-box-button")));
        System.out.println("malak");



        String actualUrl = driver.getCurrentUrl();
        System.out.println("Actual URL: " + actualUrl);

        String expectedUrl = "https://demo.nopcommerce.com/samsung-galaxy-s24-256gb";  // <-- adjust this based on actual banner link
        System.out.println("Expected URL: " + expectedUrl);

        Assert.assertEquals(actualUrl, expectedUrl, "Slider redirection URL mismatch.");
    }
}
