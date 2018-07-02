package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

/**
 * An implementation of the Element interface. Delegates its work to an underlying WebElement instance for
 * custom functionality.
 */
public class ElementImpl extends RemoteWebDriver implements Element {
    static Logger LOGGER = org.apache.log4j.Logger.getLogger(ElementImpl.class);

    private final WebElement element;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ElementImpl(final WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }


    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    //Comment out on old Selenium versions
    @Override
    public Rectangle getRect() {
        return null;
    }


    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public void clear() {
        getWrappedElement().clear();
        //throw new NoSuchMethodError("Method clear is only available in the " + TextInput.class.getName() + "class.");
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public boolean elementWired() {
        return (element != null);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target)
            throws WebDriverException {
        if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
            return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
        }
        return null;
    }
}
