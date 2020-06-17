package tutorial;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class PrepareTesting {
    @BeforeSuite
    public void beforeSuite()
    {
        System.setProperty("webdriver.chrome.driver","D:\\jars\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","D:\\jars\\geckodriver.exe");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.clearProperty("webdriver.chrome.driver");
        System.clearProperty("webdriver.gecko.driver");
    }
}
