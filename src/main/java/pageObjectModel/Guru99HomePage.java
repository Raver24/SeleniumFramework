package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99HomePage {
    WebDriver driver;
    By homePageUserName = By.xpath("//table//tr[@class='heading3']");

    public Guru99HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getHomePageDashboardUserName(){
        return driver.findElement(homePageUserName).getText();
    }


}
