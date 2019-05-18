package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private Utils() {
    }

    public static void waitUntilLocator(WebDriverWait wait, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForMilliseconds(WebDriver driver, long milliseconds) {
        // driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
