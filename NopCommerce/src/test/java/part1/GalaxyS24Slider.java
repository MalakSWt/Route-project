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

import java.util.List;

public class GalaxyS24Slider {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void homeslider2(){
        List<WebElement> sliders =wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("a.nivo-imageLink"),1));
        WebElement secondSlider = sliders.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(secondSlider)).click();
        wait.until(ExpectedConditions.urlContains("nopcommerce.com"));
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demo.nopcommerce.com/samsung-galaxy-s24-256gb";
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
