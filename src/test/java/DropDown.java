import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DropDown {
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
    public void dropDownTest()
    {
        this.baseUrl ="http://demo.guru99.com/test/newtours/register.php";
        driver.get(baseUrl);

        Select drpCountry = new Select(driver.findElement(By.name("country")));
        drpCountry.selectByVisibleText("ANTARCTICA");

        List<WebElement> selectedCountryOptions = drpCountry.getAllSelectedOptions();
        Assert.assertEquals(selectedCountryOptions.size(), 1);
        Assert.assertEquals(selectedCountryOptions.get(0).getAttribute("value"), "ANTARCTICA");
        this.baseUrl ="http://jsbin.com/osebed/2";
        driver.get(baseUrl);

        Select fruits = new Select(driver.findElement(By.id("fruits")));
        fruits.selectByVisibleText("Banana");
        fruits.selectByIndex(1);

        List<WebElement> selectedFruitOptions = fruits.getAllSelectedOptions();
        Assert.assertEquals(selectedFruitOptions.size(), 2);
        Assert.assertEquals(selectedFruitOptions.get(0).getAttribute("value"), "banana");
        Assert.assertEquals(selectedFruitOptions.get(1).getAttribute("value"), "apple");

        driver.close();
    }
}
