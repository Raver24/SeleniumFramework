package tests.tools;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class JSWebElement implements WebElement{

    private final WebDriver driver;

    private WebElement element;
    private final List<WebDriverEventListener> eventListeners =
            new ArrayList<>();
    private final WebDriverEventListener dispatcher = (WebDriverEventListener) Proxy
            .newProxyInstance(
                    WebDriverEventListener.class.getClassLoader(),
                    new Class[] {WebDriverEventListener.class},
                    (proxy, method, args) -> {
                        try {
                            for (WebDriverEventListener eventListener : eventListeners) {
                                method.invoke(eventListener, args);
                            }
                            return null;
                        } catch (InvocationTargetException e) {
                            throw e.getTargetException();
                        }
                    }
            );
    public JSWebElement(WebDriver driver) {
        this.driver = driver;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
    public void click() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void submit() {

    }

    public void sendKeys(CharSequence... keysToSend) {

    }

    public void clear() {

    }

    public String getTagName() {
        return null;
    }

    public String getAttribute(String name) {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String getText() {
        return null;
    }

    public List<WebElement> findElements(By by) {
        return null;
    }

    public JSWebElement findElement(By by) {
        dispatcher.beforeFindBy(by, null, driver);
        WebElement temp = driver.findElement(by);
        dispatcher.afterFindBy(by, temp, driver);
        this.setElement(temp);
        return this;
    }

    public boolean isDisplayed() {
        return false;
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSize() {
        return null;
    }

    public Rectangle getRect() {
        return null;
    }

    public String getCssValue(String propertyName) {
        return null;
    }


    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
