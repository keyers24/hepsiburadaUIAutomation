package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class productReviewEvaluationPage extends baseTest {

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement oneTrustBtn;
    @FindBy(id = "myAccount")
    public WebElement myAccountBtn;
    @FindBy(xpath = "//div[contains(@class,'SearchBoxOld')]/div/div")
    public WebElement searchDiv;

    @FindBy(xpath = "//div[contains(@class,'SearchBoxOld')]/div/div/div/div/div/input")
    public WebElement searchInput;

    @FindBy(xpath = "//a[@class='yPPu6UogPlaotjhx1Qki']")
    public WebElement evaluationBtn;


    @FindBy(xpath = "//div[@class='arrowUpOrange']")
    public WebElement dropdown;

    @FindBy(xpath = "//div[@class='hermes-Sort-module-vJqiqyAGHsTNXjMsIwJD']//div[2]")
    public WebElement Latest;

    @FindBy(xpath = "//div[@class='paginationContentHolder']//div[1]//div[2]//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]")
    public WebElement like;

    @FindBy(xpath = "//div[@class='hermes-ReviewCard-module-QA5PqdPP5EhkpY_vptfv']//span[@class='hermes-ReviewCard-module-eFtSSTU4lYZLCnzHtzca'][contains(text(),'Teşekkür Ederiz.')]")
    public WebElement alert;
    private static final Logger log = Logger.getLogger("productReviewEvaluationPage");

    Actions actions;
    public productReviewEvaluationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    public void navigateToWebsiteMethod(){
        click(oneTrustBtn);
        checkElement(myAccountBtn);

    }
    public void theRelevantIsSearchedForMethod(String product){
        waitForPageToLoad(driver);
        searchDiv.click();
        waitForPageToLoad(driver);
        sendKeysElement(searchInput,product);
        searchInput.sendKeys(Keys.ENTER);
    }
    public void randomlySelectedProductMethod(){
        waitForPageToLoad(driver);
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[id='1'] li div a"))
        );

        if (products.isEmpty()) {
            log.warning("product list not found");
        }

        log.info("number of products : "+products.size());

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement randomProduct = products.get(randomIndex);

        // Scroll and Clickable
        actions.moveToElement(randomProduct).perform();
        wait.until(ExpectedConditions.elementToBeClickable(randomProduct));
        randomProduct.click();

    }
    public void theDetailPageOfTheRelevantProductIsAccessedMethod(){
        waitForPageToLoad(driver);
        switchToNewTabAndFindElement(driver);
        click(evaluationBtn);
    }

    public void theReviewsTabIsNavigatedToAndSortByNewestReviewIsSelectedMethod(){
        waitForPageToLoad(driver);
        actions.moveToElement(dropdown).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        click(Latest);
    }

    public void oneOfTheOptionsEitherThumbsUpOrThumbsDownIsSelectedMethod(){
        waitForPageToLoad(driver);
        actions.moveToElement(like).perform();
        wait.until(ExpectedConditions.elementToBeClickable(like));
        click(like);

    }
    public void theSelectionIsConfirmedMethod(){
        Assert.assertEquals(alert.getText(),"Teşekkür Ederiz.");
    }
}
