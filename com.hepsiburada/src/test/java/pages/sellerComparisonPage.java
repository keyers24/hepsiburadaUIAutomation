package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.logging.Logger;

public class sellerComparisonPage extends baseTest {

    @FindBy(xpath = "//button[@data-test-id='addToCart']")
    public WebElement basketBtn;

    @FindBy(css = "div.seller-list-item")
    public WebElement sellerList;
    private static final Logger log = Logger.getLogger("sellerComparisonPage");
    Actions actions;


    public sellerComparisonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }


    public void VisibilityOfOtherVendorsIsCheckedMethod() throws InterruptedException {
        waitForPageToLoad(driver);
        switchToNewTabAndFindElement(driver);
        List<WebElement> otherSellersTab = driver.findElements(By.xpath("//a[contains(text(),'Diğer Satıcılar')]"));
        if (!otherSellersTab.isEmpty()) {
            otherSellersTab.get(0).click();

            checkElement(sellerList);

            List<WebElement> sellerItems = driver.findElements(By.cssSelector("div.seller-list-item"));
            double lowestPrice = Double.MAX_VALUE;
            WebElement lowestPriceButton = null;

            for (WebElement item : sellerItems) {
                try {
                    String priceText = item.findElement(By.cssSelector("span.price")).getText()
                            .replaceAll("[^\\d,]", "")
                            .replace(",", ".");
                    double price = Double.parseDouble(priceText);

                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestPriceButton = item.findElement(By.xpath("//button[@class='sf-Axjyr dMglHs sdfe4toxsxi v5EsDEc7HGg2J9hUA2ag']"));
                        actions.moveToElement(basketBtn);
                        click(basketBtn);
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing price or finding button: " + e.getMessage());
                }
            }


            if (lowestPriceButton != null) {
                lowestPriceButton.click();
                System.out.println("Lowest priced product added to the cart: " + lowestPrice);
            } else {
                System.out.println("No valid sellers found in 'Diğer Satıcılar'.");
            }

        } else {

            actions.moveToElement(basketBtn);
           click(basketBtn);
           Thread.sleep(3000);
        }


    }








    public void selectLowestPrice(WebDriver driver) {
        // Tüm fiyat elementlerini listeye al
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@data-test-id='price-current-price']"));

        // Elementlerin sayısını kontrol et
        if (priceElements.isEmpty()) {
            System.out.println("Fiyat bilgisi bulunamadı.");
            return;
        }

        // Eğer sadece 1 element varsa, direkt işlem yap
        if (priceElements.size() == 1) {
            WebElement singleElement = priceElements.get(0);
            System.out.println("Tek fiyat bulundu: " + singleElement.getText());
            singleElement.click(); // Direkt tıkla
//            driver.findElement(By.className("sf-AxhDW.cauFev.s3zdtjqpd3s.z8QiDixMPSOOi87wSJ1D")).click();
            click(basketBtn);
            return;
        }

        // Birden fazla element varsa en düşük fiyatı bul ve işlem yap
        WebElement lowestPriceElement = null;
        double lowestPrice = Double.MAX_VALUE;

        // Her bir elementin fiyatını kontrol et
        for (WebElement element : priceElements) {
            // Fiyat bilgisini text olarak al
            String priceText = element.getText().replaceAll("[^0-9,]", ""); // Sadece sayıları ve virgülleri al
            priceText = priceText.replace(",", "."); // Virgülü noktaya çevir (örneğin, 55,99 -> 55.99)
            double price = Double.parseDouble(priceText); // String'i double'a çevir

            // En düşük fiyatı bul
            if (price < lowestPrice) {
                lowestPrice = price;
                lowestPriceElement = element;
            }
        }

        // En düşük fiyatı içeren elemana tıkla
        if (lowestPriceElement != null) {
            System.out.println("En düşük fiyat: " + lowestPrice);
            lowestPriceElement.click(); // En düşük fiyatı içeren elemente tıklama
            //.sf-Axjyr.eQefqi.s7xeyzkwwhx.v5EsDEc7HGg2J9hUA2ag
            //driver.findElement(By.className(".sf-Axjyr.eQefqi.s7xeyzkwwhx.v5EsDEc7HGg2J9hUA2ag")).click();
            click(basketBtn);

        }
    }
}
