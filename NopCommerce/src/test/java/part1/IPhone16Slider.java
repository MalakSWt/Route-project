package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IPhone16Slider {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void homeslider1(){
        WebElement firstslider = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nivoSlider a:nth-child(1) > img")));
        firstslider.click();

        wait.until(ExpectedConditions.urlContains("nopcommerce.com"));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demo.nopcommerce.com/apple-iphone-16-128gb";
        Assert.assertEquals(actualUrl, expectedUrl);

    }
}
