package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        driver.get("https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F");


    }
}
