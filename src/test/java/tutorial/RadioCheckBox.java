package tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RadioCheckBox {

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
    public void radioCheckBoxTest()
    {
        this.baseUrl = "http://demo.guru99.com/test/radio.html";
        driver.get(baseUrl);

        WebElement radio1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radio2 = driver.findElement(By.id("vfb-7-2"));

        radio1.click();
        Assert.assertTrue(radio1.isSelected());

        radio2.click();
        Assert.assertTrue(radio2.isSelected());

        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        option1.click();
        Assert.assertTrue(option1.isSelected());

        this.baseUrl = "http://demo.guru99.com/test/facebook.html";
        driver.get(baseUrl);

        WebElement chkFBPersist = driver.findElement(By.id("persist_box"));
        for (int i = 0; i < 2; i++) {
            chkFBPersist.click();
            if (i == 0) {
                Assert.assertTrue(chkFBPersist.isSelected());
            }
            else{
                Assert.assertFalse(chkFBPersist.isSelected());
            }
        }

        driver.close();
    }
}
