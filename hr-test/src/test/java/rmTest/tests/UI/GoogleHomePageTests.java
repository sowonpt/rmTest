package rmTest.tests.UI;

import org.testng.annotations.Test;
import RmTest.framework.core.BaseTest;
import rmTest.pageobjects.homepage.GoogleHomepage;

public class GoogleHomePageTests extends BaseTest {

    @Test
    public void dummyTest() {

    }

//    @Test
    public void homepageTests() {
        GoogleHomepage googleHomepage = new GoogleHomepage(getDriver());
    }
}
