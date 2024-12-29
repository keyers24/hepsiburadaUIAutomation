package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class baseTest {
    WebDriver driver;
    WebDriverWait wait;

    Actions actions;
    private static final Logger log = Logger.getLogger("baseTest");
    public baseTest(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        this.actions=new Actions(driver);
    }

    public WebElement checkElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement clickableElement(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        WebElement visibleElement = checkElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(visibleElement)).click();
    }

    public void clickElementWithText(List<WebElement> elements, String text) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        boolean found = false;
        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                click(element);
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Aranan metni içeren element bulunamadı: " + text);
    }

    public void sendKeysElement(WebElement element, String text) {
        checkElement(element).sendKeys(text);
    }

    public void keyTheKeypad(WebElement element, Keys keyboard) {
        checkElement(element).sendKeys(keyboard);
    }


    public void scrollToElement(WebElement element) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();",element);
    }

    public WebElement checkElementFromBy(By key){

        return  wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }
    public  void clickFromBy(By key){
        WebElement element= checkElementFromBy(key);
        element.click();

    }
    public void waitForPageToLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String readyState = js.executeScript("return document.readyState").toString();

            if (readyState.equals("complete")) {
                break;
            }
        }
    }
    public void switchToNewTabAndFindElement(WebDriver driver) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}
