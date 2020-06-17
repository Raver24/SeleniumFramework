package tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoA {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            Reporter.log("The browser is opened now");
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            Reporter.log("The browser is opened now");
        }
        driver.manage().window().maximize();
        Reporter.log("The browser is maximized now");
    }

    @Test
    public void run() {
        driver.get("https://www.google.co.in");
        Reporter.log("The google website is opened");
        driver.findElement(By.name("q")).sendKeys("Hi");
        Reporter.log("The data 'Hi' is entered");

        driver.close();
        Reporter.log("The browser is closed");

    }
}
