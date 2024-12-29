package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.sellerComparisonPage;
import util.driverOperations;

public class sellerComparisonStepDefinitions {

    pages.sellerComparisonPage sellerComparisonPage=new sellerComparisonPage(driverOperations.getDriver());

    @And("Visibility of other vendors is checked.")
    public void VisibilityOfOtherVendorsIsChecked() throws InterruptedException {
        sellerComparisonPage.VisibilityOfOtherVendorsIsCheckedMethod();

    }

    @And("Price information is compared between sellers")
    public void priceInformationIsComparedBetweenSellers() {

    }

    @And("The cheaper product is added to the cart")
    public void theCheaperProductIsAddedToTheCart() {

    }

    @Then("Basket is checked")
    public void basketIsChecked() {
    }
}
