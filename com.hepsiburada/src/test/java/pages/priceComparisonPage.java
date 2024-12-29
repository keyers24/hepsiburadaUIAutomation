package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.logging.Logger;

public class priceComparisonPage extends baseTest{

    @FindBy(xpath = "(//div[@data-test-id='price-current-price'])[1]")
    public WebElement priceElement;

    @FindBy(xpath = "//button[@data-test-id='addToCart']")
    public WebElement basketBtn;
    @FindBy(css = "#basket_payedPrice > span")
    public WebElement cartPriceElement;
    private static final Logger log = Logger.getLogger("priceComparisonPage");
    Actions actions;
    private double productPrice;

    public priceComparisonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public void CheckPriceOfTheSelectedProductMethod(){
        waitForPageToLoad(driver);
        String productPriceText = priceElement.getText().replaceAll("[^\\d,]", "").replace(",", ".");
        setProductPrice(Double.parseDouble(productPriceText));

    }

    public void productIsAddedToCartMethod() throws InterruptedException {
        waitForPageToLoad(driver);
        switchToNewTabAndFindElement(driver);
        actions.moveToElement(basketBtn);
        click(basketBtn);
        Thread.sleep(3000);
    }

    public void priceEqualityIsVerifiedMethod(){
        driver.get("https://checkout.hepsiburada.com/sepetim");
        waitForPageToLoad(driver);

        String cartPriceText = cartPriceElement.getText().replaceAll("[^\\d,]", "").replace(",", ".");
        double cartPrice = Double.parseDouble(cartPriceText);


        if (Math.abs(getProductPrice() - cartPrice) < 0.01) {

            log.info("Fiyatlar eşit: " + getProductPrice());
        } else {

            log.info("Fiyatlar eşit değil. Ürün sayfası fiyatı: " + productPrice + ", Sepet fiyatı: " + cartPrice);
        }
    }
}
