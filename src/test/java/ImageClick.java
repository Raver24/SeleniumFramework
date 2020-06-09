import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ImageClick {

    private WebDriver driver;
    private String baseUrl;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) driver = new FirefoxDriver();
        else if (browser.equalsIgnoreCase("chrome")) driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void imageClickTest(){
        this.baseUrl = "https://www.facebook.com/login/identify?ctx=recover";
        driver.get(baseUrl);

        driver.findElement(By.cssSelector("a[title=\"Przejdź na stronę główną Facebooka\"]")).click();

        Assert.assertEquals(driver.getTitle(), "Facebook – zaloguj się lub zarejestruj");

        driver.close();
    }

}
