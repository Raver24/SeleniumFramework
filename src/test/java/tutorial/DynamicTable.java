package tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicTable {
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
    public void dynamicTableTest() throws ParseException {
        this.baseUrl = "http://demo.guru99.com/test/web-table-element.php";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List col = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
        Assert.assertEquals(col.size(), 5);

        List rows = driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr/td[1]"));
        Assert.assertEquals(rows.size(), 26);

        WebElement baseTable = driver.findElement(By.tagName("table"));
        WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
        String rowText = tableRow.getText();
        System.out.println("Third row of table : "+rowText);

        WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is : " + valueIneed);

        String max = "";
        double m=0, r=0;
        for (int i = 0; i < rows.size(); i++) {
            max = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/table/tbody/tr/td[4]")).getText();
            NumberFormat f = NumberFormat.getNumberInstance();
            Number num = f.parse(max);
            max = num.toString();
            m = Double.parseDouble(max);
            if (m > r){
                r = m;
            }
        }
        System.out.println("Maximum current price is : "+ r);

        this.baseUrl = "http://demo.guru99.com/test/table.html";
        driver.get(baseUrl);

        WebElement myTable = driver.findElement(By.xpath("/html/body/table/tbody"));
        List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        for (int i = 0; i < rows_count; i++) {
            List<WebElement> columns_row = rows_table.get(i).findElements(By.tagName("td"));
            int columns_count = columns_row.size();
            System.out.println("Number of cells In Row " + i + " are " + columns_count);
            for (int j = 0; j < columns_count; j++) {
                String cellText = columns_row.get(j).getText();
                System.out.println("Cell Value of row number " + i + " and column number " + j + " Is " + cellText);
            }
            System.out.println("-------------------------------------------------- ");
        }
        driver.close();
    }
}
