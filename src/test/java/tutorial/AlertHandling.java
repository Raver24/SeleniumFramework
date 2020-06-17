package tutorial;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AlertHandling {
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
    public void alertHandlingTest(){
        this.baseUrl = "http://demo.guru99.com/test/delete_customer.php";
        driver.get(baseUrl);

        driver.findElement(By.name("cusid")).sendKeys("53920");
        driver.findElement(By.name("submit")).submit();

        Alert alert = driver.switchTo().alert();

        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "Do you really want to delete this Customer?");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        alert.accept();

        this.baseUrl = "http://demo.guru99.com/popup.php";
        driver.get(baseUrl);

        driver.findElement(By.xpath("//*[contains(@href, 'popup.php')]")).click();
        String mainWindow = driver.getWindowHandle();

        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()){
            String childWindow = i1.next();
            if(!mainWindow.equalsIgnoreCase(childWindow)){
                driver.switchTo().window((childWindow));
                driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");
                driver.findElement(By.name("btnLogin")).click();
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        driver.close();
    }
}
