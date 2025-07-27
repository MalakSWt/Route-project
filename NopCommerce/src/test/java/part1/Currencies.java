package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Currencies {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void Currecy(){
        driver.findElement(By.id("customerCurrency")).click();

        driver.findElement(By.xpath("//select[@id='customerCurrency']/option[text()='Euro']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> priceElements = driver.findElements(By.cssSelector("span.price.actual-price"));

        for (int i = 0 ;i<priceElements.size();i++)
        {
            String price = priceElements.get(i).getText();

            Assert.assertTrue(price.contains("€"),"Product " + (i + 1) + " does not contain Euro symbol.");
            System.out.println(price);
        }



    }

}
