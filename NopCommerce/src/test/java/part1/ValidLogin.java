package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidLogin {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void validlogin(){
        driver.findElement(By.className("ico-login")).click();
        driver.findElement(By.className("email")).sendKeys("test@example.com");
        driver.findElement(By.className("password")).sendKeys("P@ssw0rd");
        driver.findElement(By.cssSelector("button.login-button")).click();
        SoftAssert softAssert = new SoftAssert();
        String expectedurl = "https://demo.nopcommerce.com/";
        String actualurl = driver.getCurrentUrl();
        softAssert.assertEquals(actualurl,expectedurl);
        WebElement myaccount = driver.findElement(By.className("ico-account"));
        softAssert.assertTrue(myaccount.isDisplayed());
        softAssert.assertAll();




    }


}
