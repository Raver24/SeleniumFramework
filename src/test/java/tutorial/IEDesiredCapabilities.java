package tutorial;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

public class IEDesiredCapabilities {
    private WebDriver driver;
    private String baseUrl;

    @Test
    public void IEDesiredCapabilitiesTest() {


        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        System.setProperty("webdriver.ie.driver", "D:\\jars\\IEDriverServer.exe");
        driver = new InternetExplorerDriver(options);
        driver.manage().window().maximize();
        this.baseUrl = "http://gmail.com";
        driver.get(baseUrl);

        driver.quit();

    }
}
