import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Form {

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

    @Parameters({"address", "passwd"})
    @Test
    public void formTest(String address, String passwd)
    {
        baseUrl = "http://demo.guru99.com/test/login.html";

        driver.get(baseUrl);

        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("passwd"));

        email.sendKeys(address);
        password.sendKeys(passwd);
        Assert.assertEquals(email.getAttribute("value"), address);
        Assert.assertEquals(password.getAttribute("value"), passwd);

        email.clear();
        password.clear();
        Assert.assertEquals(email.getAttribute("value"), "");
        Assert.assertEquals(password.getAttribute("value"), "");

        WebElement login = driver.findElement(By.id("SubmitLogin"));

        email.sendKeys(address);
        password.sendKeys(passwd);
        login.click();

        driver.get(baseUrl);
        driver.findElement(By.id("email")).sendKeys(address);
        driver.findElement(By.id("passwd")).sendKeys(passwd);
        driver.findElement(By.id("SubmitLogin")).submit();
        driver.close();
    }
}
