package tests.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageFactory.Guru99HomePage;
import pageFactory.Guru99Login;

import java.util.concurrent.TimeUnit;

public class Test99GuruLogin {
    String driverPath = "D:\\jars\\chromedriver.exe";
    WebDriver driver;
    Guru99Login objLogin;
    Guru99HomePage objHomePage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/V4/");
    }
    /**

     * This test case will login in http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */
    @Test(priority = 0)
    public void test_Home_Page_Appear_Correct(){
        objLogin = new Guru99Login(driver);
        String loginpageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginpageTitle.toLowerCase().contains("guru99 bank"));
        objLogin.loginToGuru("mgr123", "mgr!23");

        objHomePage = new Guru99HomePage(driver);
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
    }

    @AfterTest
    public void close(){
        driver.close();
    }

}
