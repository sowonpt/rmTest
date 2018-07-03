package rmTest.tests.UI.Search;

import com.google.common.base.Verify;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import rmTest.pagecontainers.BasePageContainer;
import rmTest.pageobjects.BasePage;
import rmTest.pageobjects.BaseTest;
import rmTest.pageobjects.HomePage;
import utils.PropertiesLoader;
import utils.VUtils;
import utils.VerifyUtils;

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
    public void userVisitsHomepage() {
        Verify.verify(true,"Homepage did not load successfully", BasePageContainer.HomePage.getAttribute("data-gr-c-s-loaded").equals("true"));
        throw new PendingException("Page loaded");
    }

    @And("^the user searches for property to buy by 'location'$")
    public void theUserSearchesForPropertyToBuyByLocation(String Location) {
        BasePageContainer.SearchProperty.sendKeys(Location);
        LOGGER.info("Entering location");
        }

    @Then("^the user sees the sale search criteria$")
    public void theUserSeesTheSaleSearchCriteria() {
        VUtils.elementExists(BasePageContainer.BuySearchPage);
        throw new PendingException();
    }

    @When("^user enters search criteria as follows$")
    public void userEntersSearchCriteriaAsFollows(String radius, String minPrice, String maxPrice, String minBedrooms, String maxBedrooms, String PropertyType) {
        Map<String, String> data = DataTable.create((radius,minPrice,maxPrice,minBedrooms,maxBedrooms,PropertyType))
        selectFilter(data, (BasePageContainer.FilterRadius, BasePageContainer.FilterMinPrice, BasePageContainer.FilterMaxPrice, BasePageContainer.FilterMinBed, BasePageContainer.FilterMaxBed, BasePageContainer.FilterPropType));
        LOGGER.info("Selecting search criteria");
    }

    @And("^user applies the filters$")
    public void userAppliesTheFilters() throws Throwable {
        wait(1);
        BasePageContainer.Submit.click();
        throw new PendingException();
    }

    @Then("^user sees search results$")
    public void userSeesSearchResults() {
        VerifyUtils.equals(BasePageContainer.SearchResultPage.getAttribute("class"),("l-propertySearch"));
    }

    @And("^the user selects a non-featured property$")
    public void theUserSelectsANonFeaturedProperty() {
        List<WebElement> propCards = BasePageContainer.PropCard.findElements(By.name("//[text() = 'heading']"));
        for (WebElement propCard : propCards) {
            if (!propCard.getAttribute("Featured Property").equals("false")) {
                propCard.click();
                throw new PendingException();
            }
        }
    }

    @Then("^the user sees the property details page$")
    public void theUserSeesThePropertyDetailsPage() {
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

