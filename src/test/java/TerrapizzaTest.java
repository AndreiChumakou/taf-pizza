import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
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
        Util.addProductToCart(driver, TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID, TerrapizzaPage.KIND_OF_PRODUCT_XPATH);

        Util.waitForPresenceByXPath(driver, 10000, TerrapizzaPage.OPEN_ORDER_PAGE_BTN);
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_BTN)).click();

        Util.waitForPresenceByXPath(driver, 20000, TerrapizzaPage.CART_OPEN_COMPLETE);
        Assertions.assertTrue(driver.findElement(By
                        .xpath("//div[@class='basket__products-item-name'][contains(text(), 'Пицца Маргарита')]"))
                .getText().equals(TerrapizzaPage.SORT_PRODUCT));
    }

    @Test
    public void testAddingSomeProductsToCart() {
        ArrayList<String> listOfOrder = new ArrayList<>();

        Util.openCategoryMenu(driver, TerrapizzaPage.CATEGORY_MENU_TEXT_PRODUCT);
        Util.addProductToCart(driver, TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID, TerrapizzaPage.KIND_OF_PRODUCT_XPATH);
        listOfOrder.add(TerrapizzaPage.KIND_OF_PRODUCT_DATA_ID);

        Util.openCategoryMenu(driver, TerrapizzaPage.CATEGORY_MENU_TEXT_BAR);
        Util.addProductToCart(driver, TerrapizzaPage.KIND_OF_BEVERAGE_DATA_ID, TerrapizzaPage.KIND_OF_BEVERAGE_XPATH);
        listOfOrder.add(TerrapizzaPage.KIND_OF_BEVERAGE_DATA_ID);

        Util.waitForPresenceByXPath(driver, 10000, TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCTS_BTN);
        driver.findElement(By.xpath(TerrapizzaPage.OPEN_ORDER_PAGE_SOME_PRODUCTS_BTN)).click();

        Util.waitForPresenceByXPath(driver, 20000, TerrapizzaPage.CART_OPEN_COMPLETE);
        List<WebElement> list =  driver.findElements(By.xpath(TerrapizzaPage.LIST_PRODUCTS));

        for (int i = 0; i < listOfOrder.size()-1; i++) {
            Assertions.assertEquals(listOfOrder.get(i), list.get(i).getAttribute(TerrapizzaPage.CART_PRODUCTS_ATTRIBUTE));
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
