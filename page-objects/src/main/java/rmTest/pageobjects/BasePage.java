package rmTest.pageobjects;

import com.google.common.base.Verify;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import rmTest.pagecontainers.BasePageContainer;
import utils.ElementFactory;
import utils.PropertiesLoader;
import org.apache.log4j.Logger;
import utils.VUtils;
import utils.VerifyUtils;

import java.util.List;
import java.util.Map;

public class BasePage {

    public static class HomePage extends BaseTest {
        final static String URL = PropertiesLoader.getProperty("ui_url");
        WebDriver driver = BaseTest.getBrowser();
        BasePageContainer basePestContainer = this.getDataContainer(BasePageContainer.class);
        private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseTest.class);

        public HomePage() {
            super();
        }


        public static <T extends BasePageContainer> T getDataContainer(Class<T> className) {
            T boundDataContainer = (T) ElementFactory.initElements(BaseTest.getBrowser(), className);
            return boundDataContainer;
        }

        public static HomePage openPage() {
            BaseTest.getBrowser().get(URL);
            return new HomePage();

        }


    }

//    private static final int TIMEOUT = 5; //seconds
//    private static final int POLLING = 100; //milliseconds
//
//    protected WebDriver driver;
//    private WebDriverWait wait;
//
//    public BaseTest(WebDriver driver) {
//        this.driver = driver;
//        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
//        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
//    }
//
//    protected void waitForElementToAppear(By locator) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }

//    protected void waitForElementToDisappear(By locator) {
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//    }
//
//    protected void waitForTextToDisappear(By locator, String text) {
//        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
//    }
}
