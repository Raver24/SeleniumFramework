package tests.restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePostTest {

    @Test
    public void postTest(){
        RestAssured.baseURI ="http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "w34hgrtjh");
        requestParams.put("LastName", "rtjnhrt6jn");
        requestParams.put("UserName", "njhtrjrt");
        requestParams.put("Password", "password1");
        requestParams.put("Email", "rtjhnrtfj@gmail.com");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post("/register");

        ResponseBody body = response.getBody();
        if (response.getStatusCode() == 200) {
            RegistrationFailureResponse responseBody = body.as(RegistrationFailureResponse.class);
            Assert.assertEquals("User already exists", responseBody.FaultId);
            Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
        }
        else if (response.getStatusCode() == 201){
            RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
            Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
            Assert.assertEquals("Operation completed successfully", responseBody.Message);
        }

    }
}
