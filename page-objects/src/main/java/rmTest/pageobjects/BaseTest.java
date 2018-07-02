package rmTest.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;
import utils.PropertiesLoader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static final String BROWSER_TYPE = System.getProperty("Browser");
//    static final String LOCAL_SELENIUM_GRID_URL = "http://localhost:";
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    //    public static final String REMOTE_BROWSER = System.getProperty("Remote");
    static WebDriver driver;
    private static String text;

    public static WebDriver getBrowser() {
        LOGGER.info("getBrowser()");
        WebDriver mBrowser;
        String browserType = BROWSER_TYPE;
        if (browserType != null) {
            if (browserType.equalsIgnoreCase("ff")) {
                mBrowser = new FirefoxDriver();
            } else if (browserType.equalsIgnoreCase("chrome")) {
                mBrowser = getChromeDriver(false);
            } else if (browserType.equalsIgnoreCase("chromeheadless")) {
                mBrowser = getChromeDriver(true);
            }
//            else if (browserType.equalsIgnoreCase("safari")) {
//                mBrowser = getSafariDriver();
//            } else if (browserType.equalsIgnoreCase("ie8")) {
//                mBrowser = getIEDriver("8");
//            } else if (browserType.equalsIgnoreCase("ie11")) {
//                mBrowser = getIEDriver("11");
//            } else if (browserType.equalsIgnoreCase("ios")) {
//                mBrowser = getIOSDriver();
//            } else if (browserType.equalsIgnoreCase("android")) {
//                mBrowser = getAndroidDriver();
//            } else if (browserType.equalsIgnoreCase("firefox")) {
//                mBrowser = getFirefoxBrowser("15");
//            }
            else if (browserType.equalsIgnoreCase("phantomjs")) {
                mBrowser = getPhantomDriver();
            } else if (browserType.equalsIgnoreCase("api")) {
                mBrowser = null;
            } else {
                // Default browser is set to chrome browser
                mBrowser = getChromeDriver(false);
            }

        } else {
            // Default browser is set remote firefox browser
            LOGGER.info("No browser type specified. Defaulting to using a remote firefox browser");
//            System.setProperty(REMOTE_BROWSER, "true");
            mBrowser = getFirefoxBrowser("32");
        }

        return mBrowser;
    }


    public static WebDriver newGetBrowser(String browser) {
        LOGGER.info("Getting Browser");
        String browserType = browser;
        WebDriver browserDriver;
//        switch (browserType) {
        if ("chrome".equals(browserType)) {
            browserDriver = getChromeDriver(false);

        } else if ("chromeheadless".equals(browserType)) {
            browserDriver = getChromeDriver(true);

        }  else if ("firefox".equals(browserType)) {
                browserDriver = getFirefoxBrowser("15");

        } else if ("phantomjs".equals(browserType)) {
            browserDriver = getPhantomDriver();
//            case "ie":
//                browserDriver = getIEDriver("ie11");
//                break;
//            case "safari":
//                browserDriver = getSafariDriver();
//                break;

        } else {
            browserDriver = getChromeDriver(false);

        }
        System.out.println("Opening Browser...." + browserDriver);
        browserDriver.manage().deleteAllCookies();
        return browserDriver;
    }

    private static WebDriver getPhantomDriver() {
        LOGGER.info("Getting PhantomJS();");
        WebDriver phantomBrowser;
        //Phantom will run locally due to implementation of phantomjs.exe file, please review?
        String phantomjs_dir = "src//main//resources";
        try {
            LOGGER.info("Preparing local phantomjs profile");
            DesiredCapabilities phantom = new DesiredCapabilities();
            phantom.setJavascriptEnabled(true);
            phantom.setCapability("takeSceenshot", true);
            phantom.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs_dir + "//phantomjs.exe");
            phantomBrowser = new PhantomJSDriver(phantom);

        } catch (Exception e) {
            LOGGER.info("Error to Open Phantom Driver " + e.getMessage());
            throw new RuntimeException("Cannot Start Base Test " + e);
        }
        return phantomBrowser;
    }



    public static WebDriver getFirefoxBrowser(String version) {
        LOGGER.info("getFirefoxBrowser()");
        WebDriver mBrowser;
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");

//        try
//        {
//            String remoteValue = REMOTE_BROWSER;
//            if (remoteValue != null && remoteValue.equalsIgnoreCase("true")) {
//            LOGGER.info("Getting remote firefox web driver");
//            LOGGER.info("Preparing local firefox profile");
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("browser.download.folderList", 2);
//            profile.setPreference("browser.download.manager.showWhenStarting", false);
//            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf, application/csv");
//            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
//                profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
//            profile.setPreference("browser.download.dir", System.getProperty("user.home"));
//            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xlsx,application/octet-stream,application/x-msdos-program, application/x-unknown-application-octet-stream, application/vnd.ms-powerpoint, application/excel, application/vnd.ms-publisher, application/x-unknown-message-rfc822, application/vnd.ms-excel, application/msword, application/x-mspublisher, application/x-tar, application/zip, application/x-gzip,application/x-stuffit,application/vnd.ms-works, application/powerpoint, application/rtf, application/postscript, application/x-gtar, video/quicktime, video/x-msvideo, video/mpeg, audio/x-wav, audio/x-midi, audio/x-aiff,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml");
//            profile.setAcceptUntrustedCertificates(true);
//            profile.setAssumeUntrustedCertificateIssuer(false);
//            DesiredCapabilities firefox = DesiredCapabilities.firefox();
    //   firefox.setVersion(version);
//            firefox.setPlatform(Platform.ANY);
//                firefox.setCapability("marionette", true);
//            firefox.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//            firefox.setCapability(FirefoxDriver.PROFILE, profile);

    //Uncomment line below to enable remote testing from your build machine with VM images
//                mBrowser = new RemoteWebDriver(new URL("http://9.79.12.251:4444/wd/hub"), DesiredCapabilities.firefox());

//            mBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //mBrowser.manage().window().setPosition(new Point(0,0));
    //java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    //Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
    //mBrowser.manage().window().setSize(dim);

//            } else {
                LOGGER.info("Preparing local firefox profile");
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf, application/csv");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xlsx,application/octet-stream,application/x-msdos-program, application/x-unknown-application-octet-stream, application/vnd.ms-powerpoint, application/excel, application/vnd.ms-publisher, application/x-unknown-message-rfc822, application/vnd.ms-excel, application/msword, application/x-mspublisher, application/x-tar, application/zip, application/x-gzip,application/x-stuffit,application/vnd.ms-works, application/powerpoint, application/rtf, application/postscript, application/x-gtar, video/quicktime, video/x-msvideo, video/mpeg, audio/x-wav, audio/x-midi, audio/x-aiff,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml");

    // profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
    //  profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
    // profile.setPreference("browser.helperApps.neverAsk.saveToDisk",default)
                profile.setPreference("browser.download.dir", System.getProperty("user.home"));
//
                LOGGER.info("Getting local firefox web driver");
//
    mBrowser = createFirefoxDriver(profile);
//}
//        }
//             catch (Exception e) {
//                LOGGER.error("Could not start browser for reason " + e.getMessage());
//                throw new RuntimeException("could not start Browser Factory", e);
//            }
            return mBrowser;
        }


    private static WebDriver createFirefoxDriver(FirefoxProfile firefoxProfile) {
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);

        WebDriver mBrowser = null;
        DesiredCapabilities firefox = DesiredCapabilities.firefox();
        mBrowser = new FirefoxDriver((Capabilities) options);
        mBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        mBrowser.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        return mBrowser;
    }

    private static WebDriver getChromeDriver(boolean isHeadless) {
        LOGGER.info("getChromeDriver()");
        WebDriver mBrowser;
//        String remoteValue = REMOTE_BROWSER;

//        try {
//
//            if (remoteValue != null && remoteValue.equalsIgnoreCase("true")) {
//                LOGGER.info("Getting remote chrome web driver");
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
//                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//                chromePrefs.put("profile.default_content_settings.popups", 0);
//
//                // TO TURN OFF MULTIPLE DOWNLOAD WARNING
//                chromePrefs.put("profile.default_content_settings.popups", 0);
//                chromePrefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
//                chromePrefs.put("profile.default_content_setting_values.automatic_downloads",1);
//                // TO TURN OFF DOWNLOAD PROMPT
//                chromePrefs.put("download.prompt_for_download", "false");
//                chromePrefs.put("download.default_directory", System.getProperty("user.home"));
//
//                ChromeOptions options = new ChromeOptions();
//                options.setExperimentalOption("prefs", chromePrefs);
//                if (isHeadless) {
//                    options.addArguments("headless");
//                    options.addArguments("disable-gpu");
//                    options.addArguments("disable-bundled-ppapi-flash");
//                }
//                options.addArguments("--start-maximized");
//                options.addArguments("--disable-infobars");
//                options.addArguments("--lang=en-GB");
//                DesiredCapabilities chrome = DesiredCapabilities.chrome();
//                chrome.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
//                chrome.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//                chrome.setCapability(ChromeOptions.CAPABILITY, options);
//
//                mBrowser = new ScreenShotRemoteWebDriver(new URL(LOCAL_SELENIUM_GRID_URL), chrome);
//            } else
//                {
//                LOGGER.info("Getting local chrome web driver");
//                DesiredCapabilities chrome = DesiredCapabilities.chrome();
//                System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
//                mBrowser = new ChromeDriver(chrome);

        LOGGER.info("Getting local chrome web driver");
        DesiredCapabilities chrome = DesiredCapabilities.chrome();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);

        // TO TURN OFF MULTIPLE DOWNLOAD WARNING
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        // TO TURN OFF DOWNLOAD PROMPT
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory", System.getProperty("user.home"));

        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("headless");
            options.addArguments("disable-gpu");
            options.addArguments("disable-bundled-ppapi-flash");
        }
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--start-maximized");

        System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");

        chrome.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        chrome.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        chrome.setCapability(ChromeOptions.CAPABILITY, options);
        mBrowser = new ChromeDriver(chrome);

//            }

        mBrowser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


//        } catch (Exception e) {
//            LOGGER.info("Error with chrome driver initialisation. " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
        return mBrowser;
    }

    private static FirefoxProfile getProfile() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream,image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel," +
                        "application/x-excel,application/x-msexcel,application/excel,application/pdf, application/csv");
        profile.setPreference("browser.download.dir", new File(new File(".")
                .getCanonicalPath(), PropertiesLoader.getProperty("DOWNLOAD_FOLDER_NAME")).getPath());
        return profile;
    }

    public static WebDriver getDriver() throws IOException {
        if (driver == null) {
            DesiredCapabilities dc = DesiredCapabilities.firefox();
//            dc.setCapability("marionette", false);
            dc.setCapability(FirefoxDriver.PROFILE, getProfile());
            driver = new FirefoxDriver(dc);
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}

//
//    private WebDriver driver;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        System.setProperty("headless", "false"); // You can set this property elsewhere
//        String headless = System.getProperty("headless");
//
//        ChromeDriverManager.getInstance().setup();
//        if("true".equals(headless)) {
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--headless");
//            driver = new ChromeDriver(chromeOptions);
//        } else {
//            driver = new ChromeDriver();
//        }
//    }
//
//    @AfterSuite
//    public void afterSuite() {
//        if(null != driver) {
//            driver.close();
//            driver.quit();
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//}

