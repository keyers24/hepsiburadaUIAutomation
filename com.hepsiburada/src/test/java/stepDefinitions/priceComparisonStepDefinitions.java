package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.priceComparisonPage;
import util.driverOperations;

public class priceComparisonStepDefinitions {
    priceComparisonPage priceComparisonPage = new priceComparisonPage(driverOperations.getDriver());
    @Given("Check price of the selected product")
    public void CheckPriceOfTheSelectedProduct(){
        priceComparisonPage.CheckPriceOfTheSelectedProductMethod();
    }

    @And("Product is added to cart")
    public void productIsAddedToCart() throws InterruptedException {
        priceComparisonPage.productIsAddedToCartMethod();
    }

    @Then("Price equality is verified")
    public void priceEqualityIsVerified() {
        priceComparisonPage.priceEqualityIsVerifiedMethod();
    }
}
