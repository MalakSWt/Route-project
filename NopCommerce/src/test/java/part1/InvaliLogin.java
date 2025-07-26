package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.Color;
import java.awt.*;

public class InvaliLogin {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }
    @Test
    public void invalidlog(){
        driver.findElement(By.className("ico-login")).click();
        driver.findElement(By.className("email")).sendKeys("wrong@example.com");
        driver.findElement(By.className("password")).sendKeys("P@ssw0rd");
        driver.findElement(By.cssSelector("button.login-button")).click();
        SoftAssert softAssert = new SoftAssert();
        WebElement errormessage = driver.findElement(By.cssSelector(".message-error.validation-summary-errors"));
        String actualmessage = errormessage.getText();
        softAssert.assertTrue(actualmessage.contains("Login was unsuccessful."));
        String RGBAcolor = errormessage.getCssValue("color");
        String Hexacolor = Color.fromString(RGBAcolor).asHex();
        softAssert.assertEquals(Hexacolor,"#e4434b");
        softAssert.assertAll();



    }
}
