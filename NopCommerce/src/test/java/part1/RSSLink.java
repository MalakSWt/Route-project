package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

public class RSSLink {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void rssicon(){
        driver.findElement(By.className("rss")).click();

        Set<String> allTabs = driver.getWindowHandles();
        while (allTabs.size() < 2) {
            allTabs = driver.getWindowHandles();
        }


        ArrayList<String> tabs = new ArrayList<>(allTabs);
        String newTab = tabs.get(1);
        driver.switchTo().window(newTab);


        String rssurl = driver.getCurrentUrl();
        Assert.assertEquals(rssurl,"https://demo.nopcommerce.com/new-online-store-is-open");
    }
}
