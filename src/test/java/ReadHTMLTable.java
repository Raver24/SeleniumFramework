import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadHTMLTable {
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
    public void readHTMLTableTest()
    {
        this.baseUrl = "http://demo.guru99.com/test/write-xpath-table.html";
        driver.get(baseUrl);

        String innerText = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]")).getText();
        Assert.assertEquals(innerText, "fourth cell");

        this.baseUrl = "http://demo.guru99.com/test/accessing-nested-table.html";
        driver.get(baseUrl);

        innerText = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]")).getText();
        Assert.assertEquals(innerText, "4-5-6");

        this.baseUrl = "http://demo.guru99.com/test/newtours/";
        driver.get(baseUrl);

        innerText = driver.findElement(By.xpath("//table[@width=\"270\"]/tbody/tr[4]/td")).getText();
        Assert.assertEquals(innerText, "New York to Chicago");

        innerText = driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[1]/table[2]/tbody/tr[3]/td[2]/font")).getText();
        Assert.assertEquals(innerText, "Always carry a travel first aid kit with bandages, antacids, aspirin, bee sting wipes, and other basic necessities.");

        driver.close();
    }
}
