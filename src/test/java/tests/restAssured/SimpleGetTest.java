package tests.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest {

    @Test
    public void getWeatherDetails(){
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        Headers allHeaders = response.headers();
        for (Header header : allHeaders){
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx");
        String acceptLanguage = response.header("Content-Encoding");
        Assert.assertEquals(acceptLanguage, "gzip");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String city = jsonPathEvaluator.get("City");
        System.out.println("City received from Response " + city);
        Assert.assertEquals(city, "Hyderabad");
    }
}
