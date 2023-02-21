import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TerrapizzaTest {

    WebDriver driver;

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TerrapizzaPage.URL);
    }

    @Test
    public void testAddingPizzaToCart() {
        driver.findElement(By.xpath(TerrapizzaPage.BUTTON_COOKIES_CLOSE)).click();

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
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.NAME_FIRST_PRODUCT_IN_CART)));
        String nameFirstProductInCart = driver.findElement(By.xpath(TerrapizzaPage.NAME_FIRST_PRODUCT_IN_CART)).getText();

        Assertions.assertEquals(TerrapizzaPage.SORT_PRODUCT, nameFirstProductInCart);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
