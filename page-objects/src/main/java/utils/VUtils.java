package utils;

import com.google.common.base.Stopwatch;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rmTest.pageobjects.BasePage;
import rmTest.pageobjects.BaseTest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class VUtils {

    private static int waitAcceleration;

    static {
        waitAcceleration = Integer.parseInt(PropertiesLoader.getProperty("wait.acceleration"));
    }

    static Logger LOGGER = Logger.getLogger(VUtils.class);

    public static void waitFor(int i) {
        try {
            Thread.sleep(i * 1000 / waitAcceleration);

        } catch (InterruptedException e) {

        }
    }

    public static void waitForJQuery(WebDriver driver) {
        (new WebDriverWait(driver, 240)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                System.out.println("The value for jquery.active is : " + js.executeScript("return jQuery.active"));
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }
    public static void waitForElement(WebElement defaultElement) {
        for (int i = 0; i <= 15; i++) {
            try {

                if (defaultElement.isDisplayed())
                    return;
                waitFor(1);
                LOGGER.info("Waiting for 1 sec ....");

            } catch (NoSuchElementException e) {
                LOGGER.info("Waiting for 1 sec ....");
                waitFor(1);
            }
        }
    }

    public static boolean waitForPageToLoad(WebElement element) {
        for (int i = 0; i < 3; i++) {
            try {
                if (element.isDisplayed())
                    return true;
                waitFor(1);
            } catch (NoSuchElementException e) {
                LOGGER.info("Page Loading...");
                waitFor(1);
            }
        }
        return false;
    }

    public static void captureScreen(Scenario scenario) {
        // try {
        WebDriver augmentedDriver = new Augmenter().augment(BaseTest.getBrowser());
        byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }


    public static boolean isElementPresent(WebElement element) {
        return elementExists(element);
    }

    public static boolean elementExists(WebElement webElement, By xpath) {
        try {
            return webElement.findElement(xpath).isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.info("Element not found :" + xpath);
        }
        return false;
    }

    public static boolean isElementPresentByTagName(WebElement element, By tagName) {
        try {
            return element.findElement(tagName).isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.info("Element Not Found :" + element);
        }
        return false;
    }


    public static boolean elementExists(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.info("Element not found : " + element);
        }
        return false;
    }


    public static boolean isElementWithTextPresentByDiv(String text) {
        String xPath = "//div[text()='" + text + "']";
        return isElementPresent((WebElement) By.xpath(xPath));
    }

    public static boolean isElementWithTextPresentBy(String text) {
        String xPath = "//*[text()='" + text + "']";

        return isElementPresent((WebElement) By.xpath(xPath));
        // return isElementPresent(by.id);
    }

    public static boolean isElementWithTextPresentBySpan(String text) {
        String xPath = "//span[text()='" + text + "']";
        return isElementPresent((WebElement) By.xpath(xPath));
    }

    public static int[] getRowList(String rows) {
        String[] list = rows.split(",");
        int[] rowList = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            rowList[i] = Integer.valueOf(list[i]);
        }
        return rowList;
    }

    public static Map<String, String> getSortedHashMap(DataTable dataTable) {
        Map<String, String> map = new LinkedHashMap<String, String>();


        for (List<String> drIn : dataTable.asLists(String.class)) {

            System.out.print(drIn);
            map.put(drIn.get(0), drIn.get(1));

        }

        return map;
    }

    public static void waitForAttribute(WebElement element, String attribute, String value) {

        for (int i = 0; i <= (Integer.parseInt(PropertiesLoader.getProperty("MAX_TIME_OUT"))); i++) {
            try {

                if (element.getAttribute(attribute).contains(value))
                    return;
                waitFor(1);
                LOGGER.info("Waiting for 1 sec .... for an attribute " + attribute + " with value " + value + " to be presnet");

            } catch (NoSuchElementException e) {
                LOGGER.info("Waiting for 1 sec ....to element to be visible");
                waitFor(1);
            }
        }

    }

    public static boolean isElementPresentWithLocator(String locator, String value) {
        if (locator.equals("css"))
            return isElementPresent((WebElement) By.cssSelector(value));
        return false;
    }

}
