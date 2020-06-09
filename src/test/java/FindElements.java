import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FindElements {

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
    public void findElementsTest()
    {
        baseUrl = "http://demo.guru99.com/test/ajax.html";
        driver.get(baseUrl);

        List<WebElement> elements = driver.findElements(By.name("name"));
        Assert.assertEquals(elements.size(), 2);
        driver.close();
    }
}
