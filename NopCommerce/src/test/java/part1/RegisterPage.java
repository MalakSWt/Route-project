package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }
    @Test

    public void validregister(){

        driver.findElement(By.className("ico-register")).click();
        driver.findElement(By.className("male")).click();
        driver.findElement(By.name("FirstName")).sendKeys("automation");
        driver.findElement(By.name("LastName")).sendKeys("tester");
        driver.findElement(By.name("Email")).sendKeys("test@example.com");
        driver.findElement(By.id("Password")).sendKeys("P@ssw0rd");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("P@ssw0rd");
        driver.findElement(By.id("register-button")).click();
        String actualMessage = driver.findElement(By.className("result")).getText();
        String actualColor = driver.findElement(By.className("result")).getCssValue("color");

        Assert.assertEquals(actualMessage,"Your registration completed");
        Assert.assertEquals(actualColor,"rgba(76, 177, 124, 1)");



    }
}
