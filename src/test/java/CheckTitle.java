import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckTitle {

    private WebDriver driver;
    private String baseUrl;

    @BeforeSuite
    public void beforeSuite()
    {
        System.setProperty("webdriver.chrome.driver","D:\\jars\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","D:\\jars\\geckodriver.exe");
    }
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) driver = new ChromeDriver();
        else if (browser.equalsIgnoreCase("chrome")) driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkTitleTest() {

        baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";

        driver.get(baseUrl);

        actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);

        driver.close();
    }

    @AfterSuite
    public void afterSuite()
    {
        System.clearProperty("webdriver.chrome.driver");
        System.clearProperty("webdriver.gecko.driver");
    }
}