package tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ToolTip {
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
    public void toolTipTest() {
        this.baseUrl = "http://demo.guru99.com/test/social-icon.html";
        driver.get(baseUrl);

        String expectedToolTip = "Github";

        WebElement gitHub = driver.findElement(By.xpath("//*[@class = 'soc-ico show-round']/a[4]"));
        String actualToolTip = gitHub.getAttribute("title");

        Assert.assertEquals(actualToolTip, expectedToolTip);

        this.baseUrl = "http://demo.guru99.com/test/tooltip.html";
        driver.get(baseUrl);

        expectedToolTip = "What's new in 3.2";

        WebElement downloadNow = driver.findElement(By.xpath("//*[@id='download_now']"));
        Actions builder = new Actions(driver);

        builder.clickAndHold().moveToElement(downloadNow);
        builder.moveToElement(downloadNow).build().perform();

        WebElement toolTip = driver.findElement(By.xpath("//*[@class='box']/div/a"));

        Assert.assertEquals(toolTip.getText(), expectedToolTip);
        driver.close();

    }
}
