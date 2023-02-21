import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TerrapizzaTest {

    WebDriver driver;

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TerrapizzaPage.URL);
        driver.findElement(By.xpath(TerrapizzaPage.BUTTON_COOKIES_CLOSE)).click();
    }

    @Test
    public void testAddingPizzaToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(TerrapizzaPage.NAME_PRODUCT))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.SORT_PRODUCT_XPATH)));
        String cartAttribute = driver.findElement(By.xpath(TerrapizzaPage.SORT_PRODUCT_XPATH))
                .getAttribute(TerrapizzaPage.CART_ATTRIBUTE);
        driver.findElement(By.xpath("//button[@data-id='" + cartAttribute + "']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_BTN)));
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_BTN)).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.NAME_PRODUCT_IN_CART)));
        String nameFirstProductInCart = driver.findElement(By.xpath(TerrapizzaPage.NAME_PRODUCT_IN_CART)).getText();
        Assertions.assertEquals(TerrapizzaPage.SORT_PRODUCT, nameFirstProductInCart);
    }

    @Test
    public void testAddingSomeProductsToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(TerrapizzaPage.NAME_PRODUCT))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.SORT_PRODUCT_XPATH)));
        String cartAttributeProduct = driver.findElement(By.xpath(TerrapizzaPage.SORT_PRODUCT_XPATH))
                .getAttribute(TerrapizzaPage.CART_ATTRIBUTE);
        driver.findElement(By.xpath("//button[@data-id='" + cartAttributeProduct + "']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(TerrapizzaPage.BEVERAGE_XPATH))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.SORT_BEVERAGE_XPATH)));

        String cartAttributeBeverage = driver.findElement(By.xpath(TerrapizzaPage.SORT_BEVERAGE_XPATH))
                .getAttribute(TerrapizzaPage.CART_ATTRIBUTE);
        driver.findElement(By.xpath("//button[@data-id='" + cartAttributeBeverage + "']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCT_BTN)));
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCT_BTN)).click();

        List<WebElement> list =  driver.findElements(By.xpath("//div[@class='basket__products-item-name']"));
        Assertions.assertEquals(TerrapizzaPage.SORT_PRODUCT, list.get(0).getText());
        Assertions.assertEquals(TerrapizzaPage.SORT_BEVERAGE, list.get(1).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
