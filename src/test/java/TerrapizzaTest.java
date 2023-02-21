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
import java.util.List;

public class TerrapizzaTest {

    WebDriver driver;

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TerrapizzaPage.URL);
        driver.findElement(By.xpath(TerrapizzaPage.COOKIES_BUTTON_CLOSE)).click();
    }

    @Test
    public void testAddingPizzaToCart() {
        Util.openCategoryMenu(driver, TerrapizzaPage.CATEGORY_MENU_TEXT_PRODUCT);
        Util.addProductToCart(driver, TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID);

        Util.waitForPresenceByXPath(driver, 10000, TerrapizzaPage.OPEN_ORDER_PAGE_BTN);
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_BTN)).click();

        Util.waitForPresenceByXPath(driver, 20000, TerrapizzaPage.SSS); //passed                // ИЗМЕНИТЬ
        Assertions.assertTrue(driver.findElement(By
            .xpath("//div[@class='basket__products-item-name'][contains(text(), 'Пицца Маргарита')]"))
            .getText().equals(TerrapizzaPage.SORT_PRODUCT));
    }

    @Test
    public void testAddingSomeProductsToCart() {
        Util.openCategoryMenu(driver, TerrapizzaPage.CATEGORY_MENU_TEXT_PRODUCT);
        Util.addProductToCart(driver, TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID);
        Util.waitForPresenceByXPath(driver, 10000, TerrapizzaPage.KIND_OF_PRODUCT_XPATH);
        driver.findElement(By.xpath("//button[@data-id='" + TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID + "']")).click();


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(TerrapizzaPage.CATEGORY_MENU_TEXT_BAR))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.SORT_BEVERAGE_XPATH)));

        String cartAttributeBeverage = driver.findElement(By.xpath(TerrapizzaPage.SORT_BEVERAGE_XPATH))
                .getAttribute(TerrapizzaPage.CART_ATTRIBUTE);
        driver.findElement(By.xpath("//button[@data-id='" + cartAttributeBeverage + "']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCTS_BTN)));
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCTS_BTN)).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='basket__products-item-name']")));
        List<WebElement> list =  driver.findElements(By.xpath("//div[@class='basket__products-item-name']"));

        Assertions.assertEquals(TerrapizzaPage.SORT_PRODUCT, list.get(0).getText());
        Assertions.assertEquals(TerrapizzaPage.SORT_BEVERAGE, list.get(1).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
