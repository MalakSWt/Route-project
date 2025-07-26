package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

public class FacebookLink {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void facebookicon(){
        driver.findElement(By.className("facebook")).click();
        Set<String> allTabs = driver.getWindowHandles();
        while (allTabs.size() < 2) {
            allTabs = driver.getWindowHandles();
        }


        ArrayList<String> tabs = new ArrayList<>(allTabs);
        String newTab = tabs.get(1);
        driver.switchTo().window(newTab);

        String facebookurl = driver.getCurrentUrl();
        Assert.assertEquals(facebookurl,"https://www.facebook.com/nopCommerce");
    }
}
