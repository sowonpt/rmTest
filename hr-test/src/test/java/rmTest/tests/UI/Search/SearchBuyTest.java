package rmTest.tests.UI.Search;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@BuySearch"}
        , format = {"pretty", "html:target/SmokeTests/cucumber", "json:target/SmokeTests/cucumber.json"}
        , glue = {"voyanta.ui.reportsTableau","voyanta.ui.StepDefUtil"})
public class SearchBuyTest {
}
