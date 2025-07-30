package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SearchSKU {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void Skusearch(){
        String[] skus = {"SCI_FAITH", "APPLE_CAM", "SF_PRO_11"};

        for (String sku : skus) {
            System.out.println(" Searching by SKU: " + sku);


            WebElement searchInput = driver.findElement(By.id("small-searchterms"));
            searchInput.clear();
            searchInput.sendKeys(sku);
            driver.findElement(By.cssSelector("form[action='/search'] button")).click();


            List<WebElement> productLinks = driver.findElements(By.cssSelector(".product-title > a"));
            Assert.assertTrue(productLinks.size() > 0, " No product found for SKU: " + sku);

            productLinks.get(0).click();


            WebElement skuElement = driver.findElement(By.cssSelector(".sku > span.value"));
            String displayedSKU = skuElement.getText().trim().toUpperCase();

            System.out.println(" Found SKU on product page: " + displayedSKU);

            Assert.assertTrue(displayedSKU.contains(sku),
                    " SKU mismatch: Expected to contain '" + sku + "', but was '" + displayedSKU + "'");




            driver.get("https://demo.nopcommerce.com/");
        }
    }

}
