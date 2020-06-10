import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class UploadAndDownload {
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
    public void uploadAndDownloadTest(){
        this.baseUrl = "http://demo.guru99.com/test/upload/ ";
        driver.get(baseUrl);

        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
        uploadElement.sendKeys("C:\\Users\\Łukasz\\Desktop\\asd.txt");

        driver.findElement(By.id("terms")).click();
        driver.findElement((By.name("send"))).click();


        this.baseUrl = "http://demo.guru99.com/test/yahoo.html ";
        driver.get(baseUrl);

        WebElement downloadButton = driver.findElement(By.id("messenger-download"));
        String sourceLocation = downloadButton.getAttribute("href");
        String wget_command = "cmd /c D:\\jars\\wget.exe -P D: --no-check-certificate " + sourceLocation;

        try{
            Process exec = Runtime.getRuntime().exec(wget_command);
            int exitVal = exec.waitFor();
            System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex){
            System.out.println(ex.toString());
        }

        File downloadedFile = new File("D:\\msgr11us.exe");
        downloadedFile.delete();
        driver.close();
    }
}
