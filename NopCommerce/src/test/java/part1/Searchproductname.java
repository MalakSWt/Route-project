package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Searchproductname {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void searchname(){
        String[] products = {"book", "laptop", "nike"};

        for (String product : products) {
            SoftAssert softAssert = new SoftAssert();


            WebElement searchInput = driver.findElement(By.id("small-searchterms"));
            searchInput.clear();
            searchInput.sendKeys(product);

            driver.findElement(By.cssSelector("form[action='/search'] button")).click();


            String currentUrl = driver.getCurrentUrl();
            softAssert.assertTrue(currentUrl.contains("https://demo.nopcommerce.com/search?q="),
                    "URL does not contain expected search query for: " + product);


            List<WebElement> results = driver.findElements(By.cssSelector(".product-item"));
            int resultCount = results.size();

            System.out.println(" Number of results found for '" + product + "': " + resultCount);

            softAssert.assertTrue(results.size() > 0,
                    "No search results found for: " + product);


            for (WebElement result : results) {
                String resultText = result.getText().toLowerCase();
                softAssert.assertTrue(resultText.contains(product),
                        "Search result does not contain the word: " + product + "\nText: " + resultText);
            }


            softAssert.assertAll();
        }
    }
}
