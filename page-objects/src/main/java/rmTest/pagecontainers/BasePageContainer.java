package rmTest.pagecontainers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePageContainer {

    @FindBy(how = How.ID, using = "searchLocation")
    public static WebElement SearchProperty;

    @FindBy(how = How.ID, using = "homepage")
    public static WebElement HomePage;

    @FindBy(how = How.ID, using = "buyingsearchcriteria")
    public static WebElement BuySearchPage;

    @FindBy(how = How.ID, using = "propertySearch")
    public static WebElement SearchResultPage;

    @FindBy(how = How.ID, using = "radius")
    public static WebElement FilterRadius;

    @FindBy(how = How.ID, using = "minPrice")
    public static WebElement FilterMinPrice;

    @FindBy(how = How.ID, using = "maxPrice")
    public static WebElement FilterMaxPrice;

    @FindBy(how = How.ID, using = "minBedrooms")
    public static WebElement FilterMinBed;

    @FindBy(how = How.ID, using = "maxBedrooms")
    public static WebElement FilterMaxBed;

    @FindBy(how = How.ID, using = "displayPropertyType")
    public static WebElement FilterPropType;

    @FindBy (how = How.ID, using = "maxDaysSinceAdded")
    public static WebElement IncludeSold;

    @FindBy (how = How.CLASS_NAME, using = "propertyCard-moreInfoFeaturedTitle")
    public static WebElement PropCard;

    @FindBy(how = How.CLASS_NAME, using = "primary-content property-details")
    public static WebElement PropDetails;

    @FindBy(how = How.ID, using = "primaryContent")
    public static WebElement PrimaryContent;

    }
