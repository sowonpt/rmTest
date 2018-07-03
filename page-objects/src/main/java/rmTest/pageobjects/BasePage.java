package rmTest.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import rmTest.pagecontainers.BasePageContainer;
import utils.ElementFactory;
import utils.PropertiesLoader;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import utils.VUtils;

import java.util.Map;

public class BasePage {

    final static String URL = PropertiesLoader.getProperty("ui_url");
    private BasePageContainer basePageContainer = this.getDataContainer(BasePageContainer.class);
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);
    private static WebDriver mDriver;


    public BasePage() {
        super();
    }


    public static <T extends BasePageContainer> T getDataContainer(Class<T> className) {
        T boundDataContainer = (T) ElementFactory.initElements(BaseTest.getBrowser(), className);
        return boundDataContainer;
    }

    private static boolean driverNotSet() {
        return mDriver == null;
    }

    public static WebDriver getCurrentDriver() {
        try {
            if (driverNotSet()) {
                LOGGER.info("Driver is Null ~ Getting New WebDriver");
                mDriver = BaseTest.getBrowser();
            }
        } catch (UnreachableBrowserException e) {
            LOGGER.error("Get new WebDriver, UnreachableBrowserException thrown");
            mDriver = BaseTest.getBrowser();
        } catch (WebDriverException e) {
            LOGGER.error("Get new WebDriver, WebDriverException thrown");
            mDriver = BaseTest.getBrowser();
        }
        return mDriver;
    }


    public static HomePage openPage() {
            BaseTest.getBrowser().get(URL);
            return new HomePage();
    }

    public void selectFilter(Map<String, String> data) {
        final String[] value = new String[1];
        for (String key : data.keySet()) {
            if (!data.get(key).equals("")) {
                value[0] = data.get(key);
            }
        }
    }

}
