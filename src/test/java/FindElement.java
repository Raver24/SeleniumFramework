import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class FindElement {

    private WebDriver driver;
    private String baseUrl;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) driver = new ChromeDriver();
        else if (browser.equalsIgnoreCase("chrome")) driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void findElementTest()
    {
        baseUrl = "http://demo.guru99.com/test/ajax.html";
        driver.get(baseUrl);

        driver.findElement(By.id("no")).click();
        driver.findElement(By.id("buttoncheck")).click();

        driver.close();
    }
}
