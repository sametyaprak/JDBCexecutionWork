package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static io.restassured.RestAssured.given;

public class US_04_Test_DeleteTest {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath jsonPath;


    public void deleteMethod(int queryId){
        response = given().contentType(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).when().delete(endpoint+queryId);
        response.prettyPrint();
    }
    public void getMethod(int queryId){
        response = given().accept(ContentType.JSON).when().get(endpoint+queryId);
        response.prettyPrint();
    }

    @Test
    public void TC0301(){
        response = given().accept(ContentType.JSON).when().get(endpoint);
        jsonPath = response.jsonPath();
        int sizeOfData = jsonPath.getInt("meta.pagination.total");
        int deletedId = ReusableMethods.randomInteger(sizeOfData);
        deleteMethod(deletedId);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data"),null);
        Assert.assertEquals(jsonPath.getString("meta"),null);
        Assert.assertEquals(jsonPath.getInt("code"),204);
        getMethod(deletedId);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.message"),"Resource not found");

    }
}
