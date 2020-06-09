import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class MouseKeyboardEvent {
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
    public void mouseKeyboardEventTest(){
        this.baseUrl = "http://demo.guru99.com/test/newtours/";
        driver.get(baseUrl);

        WebElement link_Home = driver.findElement(By.partialLinkText("Home"));
        WebElement td_home = driver.findElement(By.xpath("//html/body/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr"));

        Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();

        String bg_color = td_home.getCssValue("background-color");
        mouseOverHome.perform();
        Assert.assertNotEquals(td_home.getCssValue("background-color"), bg_color);

        this.baseUrl = "http://www.facebook.com/";
        driver.get(baseUrl);

        WebElement txtUserName = driver.findElement(By.id("email"));

        Action seriesOfActions = builder
                .moveToElement(txtUserName)
                .click()
                .keyDown(txtUserName, Keys.SHIFT)
                .sendKeys(txtUserName, "hello")
                .keyUp(txtUserName, Keys.SHIFT)
                .doubleClick(txtUserName)
                .contextClick()
                .build();
        seriesOfActions.perform();

        driver.close();
    }
}
