package rmTest.tests.UI.Search;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@BuySearch"}
        , format = {"pretty", "html:target/SearchBuy/cucumber", "json:target/SearchBuy/cucumber.json"}
        , glue = {"rmTest.pageobjects","rmTest.tests.UI.Search"})
public class SearchBuyTest {
}
