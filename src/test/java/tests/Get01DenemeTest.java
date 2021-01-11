package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class Get01DenemeTest {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    List<String> myStringData = new ArrayList<>();
    JsonPath jsonPath;

    public void apiGetMethod(String endpoint){
        response = given().accept(ContentType.JSON).when().get(endpoint);
        response.prettyPrint();
    }

    @Test
    public void TC01(){
        apiGetMethod(endpoint);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void TC02(){
        apiGetMethod(endpoint);
        jsonPath = response.jsonPath();
        int total = jsonPath.getInt("meta.pagination.total");
//        System.out.println(jsonPath.getList("data.id"));
//        System.out.println(jsonPath.getInt("meta.pagination.pages"));
        for(int i=1;i<=jsonPath.getInt("meta.pagination.pages");i++){
            apiGetMethod(endpoint+"?page="+i);
            jsonPath = response.jsonPath();
            myStringData.addAll(jsonPath.getList("data.id"));
        }
        System.out.println(myStringData);
        Assert.assertEquals(total,myStringData.size());
    }
}
