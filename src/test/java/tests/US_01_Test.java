package tests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class US_01_Test {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    public void getResponse(){
        response = given().accept(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).when().get(endPoint);
    }

    @Test
    public void tc0101(){
        getResponse();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void tc0102(){
        getResponse();
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }
    @Test
    public void tc0103(){
        getResponse();
        json = response.jsonPath();
        Assert.assertEquals(json.getInt("meta.pagination.total"),1903);
    }
    @Test
    public void tc0104(){
        getResponse();
        json = response.jsonPath();
        Assert.assertEquals(json.getInt("meta.pagination.total"),1903);
    }
}
