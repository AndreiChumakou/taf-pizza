import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Util {

    public static void waitForPresenceByXPath(WebDriver driver, int millis, String xPath) {
        new WebDriverWait(driver, Duration.ofMillis(millis))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }
    public static void waitForPresenceByLinkText(WebDriver driver, int millis, String linkText) {
        new WebDriverWait(driver, Duration.ofMillis(millis))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
    }

    public static void openCategoryMenu(WebDriver driver, String CATEGORY_MENU_TEXT_PRODUCT) {
        waitForPresenceByLinkText(driver, 10000, CATEGORY_MENU_TEXT_PRODUCT);
        driver.findElement(By.linkText(CATEGORY_MENU_TEXT_PRODUCT)).click();
    }

    public static void addProductToCart(WebDriver driver, String id, String kindProductXPath) {
        waitForPresenceByXPath(driver, 10000, kindProductXPath);
        driver.findElement(By.xpath("//button[@data-id='" + id + "']")).click();
    }



}
