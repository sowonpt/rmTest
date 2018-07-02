package rmTest.tests.UI.Search;

import com.google.common.base.Verify;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import rmTest.pagecontainers.BasePageContainer;
import rmTest.pageobjects.BasePage;
import rmTest.pageobjects.BaseTest;
import utils.PropertiesLoader;
import utils.VUtils;
import utils.VerifyUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BuyPageStepdefs extends BasePage {

    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BuyPageStepdefs.class);
    WebDriverWait wait;

    @Before({"@BuySearch,@Search"})
    public void start() {
        BaseTest.getBrowser().get(PropertiesLoader.getProperty("ui_url"));
        BaseTest.getBrowser().manage().window().maximize();
        HomePage homePage = new HomePage();
        this.wait = new WebDriverWait(BaseTest.getBrowser(), 30);
    }

    @Given("^user visits homepage$")
    public void userVisitsHomepage() throws Throwable {
        Verify.verify(true,"Homepage did not load successfully", BasePageContainer.HomePage.getAttribute("data-gr-c-s-loaded").equals("true"));;
        throw new PendingException("Page loaded");
    }

    @And("^the user searches for property to buy by 'location'$")
    public void theUserSearchesForPropertyToBuyByLocation(String Location) throws Throwable {
        BasePageContainer.SearchProperty.sendKeys(Location);
        LOGGER.info("Entering location");
        }

    @Then("^the user sees the sale search criteria$")
    public void theUserSeesTheSaleSearchCriteria() throws Throwable {
        VUtils.elementExists(BasePageContainer.BuySearchPage);
        throw new PendingException();
    }

    @And("^user enters search criteria as follows$")
    public void userEntersSearchCriteriaAsFollows(String Radius, String minPrice, String maxPrice, String minBedrooms, String maxBedrooms, String PropertyType) throws Throwable {
        BasePageContainer.FilterRadius.sendKeys(Radius);
        VUtils.waitFor(1);
        BasePageContainer.FilterMinPrice.sendKeys(minPrice);
        VUtils.waitFor(1);
        BasePageContainer.FilterMaxPrice.sendKeys(maxPrice);
        VUtils.waitFor(1);
        BasePageContainer.FilterMinBed.sendKeys(minBedrooms);
        VUtils.waitFor(1);
        BasePageContainer.FilterMaxBed.sendKeys(maxBedrooms);
        VUtils.waitFor(1);
        BasePageContainer.FilterPropType.sendKeys(PropertyType);
        VUtils.waitFor(1);
        if (!BasePageContainer.IncludeSold.isSelected()) {
            BasePageContainer.IncludeSold.click();
            LOGGER.info("Entering search criteria");
            throw new PendingException();
        }
    }

    @Then("^user sees search results$")
    public void userSeesSearchResults() throws Throwable {
        VerifyUtils.equals(BasePageContainer.SearchResultPage.getAttribute("class"),("l-propertySearch"));
    }

    @And("^the user selects a non-featured property$")
    public void theUserSelectsANonFeaturedProperty() throws Throwable {
        List<WebElement> okButtons = BasePageContainer.PropCard.findElements(By.name("//button[text() = 'OK']"));
        for (WebElement okButton : okButtons) {
            if (!okButton.getAttribute("Featured Property").equals("false")) {
                okButton.click();
                throw new PendingException();
            }
        }
    }

    @Then("^the user see the property details page$")
    public void theUserSeeThePropertyDetailsPage() throws Throwable {
        VerifyUtils.equals(BasePageContainer.PrimaryContent.getAttribute("class"),("primary-content property-details"));
        throw new PendingException();
    }

    @After
    public void stop(Scenario scenario) {
        if (scenario.isFailed()) {
            VUtils.captureScreen(scenario);
        }

        BaseTest.getBrowser().quit();
    }


}

