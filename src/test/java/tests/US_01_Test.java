package tests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class US_01_Test {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    List<String> dataString = new ArrayList<>();
    List<Integer> dataInteger = new ArrayList<>();

    public void getResponse(String endPoint){
        response = given().accept(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).when().get(endPoint);
    }

    @Test
    public void tc0101(){
        getResponse(endPoint);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void tc0102(){
        getResponse(endPoint);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }
    @Test
    public void tc0103(){
        getResponse(endPoint);
        json = response.jsonPath();
        Assert.assertEquals(json.getInt("meta.pagination.total"),1903);
    }
    @Test
    public void tc0104(){
        getResponse(endPoint);
        json = response.jsonPath();
        Assert.assertEquals(json.getInt("meta.pagination.pages"),96);
    }
    @Test
    public void tc0105(){
        int sum =0;
        for(int i=1;i<95;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            dataInteger.addAll(json.getList("data.id"));
        }
        System.out.println(dataInteger);
        for (int i=0;i<dataInteger.size()-1;i++){
            if(dataInteger.get(i)>dataInteger.get(i+1)){
                sum++;
                break;
            }
        }
        Assert.assertTrue(sum==0);
    }
    @Test
    public void test(){
        getResponse(endPoint+"?page="+1);
        response.prettyPrint();
    }


    @Test
    public void tc0106(){

    }
}
