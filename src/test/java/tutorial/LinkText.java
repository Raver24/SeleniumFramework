package tutorial;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import tests.tools.JSWebElement;

public class LinkText {
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
    public void linkTextTest(){
        this.baseUrl = "http://demo.guru99.com/test/link.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("click here")).click();
        Assert.assertEquals(driver.getTitle(), "Google");

        this.baseUrl = "http://demo.guru99.com/test/accessing-link.html";
        driver.get(baseUrl);

        driver.findElement(By.partialLinkText("here")).click();
        Assert.assertEquals(driver.getTitle(), "Google");

        this.baseUrl = "http://demo.guru99.com/test/newtours/";
        driver.get(baseUrl);

        String theLinkText = driver.findElement(By.partialLinkText("egis")).getText();
        Assert.assertEquals(theLinkText, "Register here");

        theLinkText = driver.findElement(By.partialLinkText("EGIS")).getText();
        Assert.assertEquals(theLinkText, "REGISTER");

        this.baseUrl = "http://demo.guru99.com/test/block.html";
        driver.get(baseUrl);
        driver.findElement(By.partialLinkText("Inside")).click();
        Assert.assertEquals(driver.getTitle(), "Google");
        driver.navigate().back();
        try {
            driver.findElement(By.partialLinkText("Outside")).click();
        }
        catch (ElementNotInteractableException e) {
            new JSWebElement(driver).findElement(By.partialLinkText("Outside")).click();
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.titleIs("Facebook – zaloguj się lub zarejestruj"));
        }
        Assert.assertEquals(driver.getTitle(), "Facebook – zaloguj się lub zarejestruj");

        driver.close();
    }
}
