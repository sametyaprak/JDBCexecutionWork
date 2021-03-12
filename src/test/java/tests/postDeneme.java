package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.User2Pojo;
import utilities.ConfigReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class postDeneme {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    Map<String,Object> myPostData = new HashMap<>();
    JsonPath jsonPath;

    public void postMethod(Object body){
        response = given().contentType(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).body(body).when().post(endpoint);
        response.prettyPrint();
    }

    @Test
    public void get(){
        response = given().when().get(endpoint);
        response.prettyPrint();
    }
    @Test
    public void post1(){
        String body = "{\n" +
                "        \"name\": \"samet\",\n" +
                "        \"email\": \"sa6262eaqet@sdfs\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"status\": \"Active\"\n" +
                "    }";
        response = given().contentType(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).body(body).when().post(endpoint);
        response.prettyPrint();
    }
    @Test
    public void post2(){
        myPostData.put("name","cengiz");
        myPostData.put("email","kerfli@gmail.com");
        myPostData.put("gender","Male");
        myPostData.put("status","Active");
        postMethod(myPostData);
        jsonPath = response.jsonPath();
//        String myResponseData = jsonPath.getString("data.name");
//        System.out.println(myResponseData);
        System.out.println(jsonPath.getString("data.field"));
        System.out.println(jsonPath.getString("data.message"));
    }
    @Test
    public void post3(){
        User2Pojo object = new User2Pojo(true,"mead@gmail.com","Female","Active");
        //User2Pojo object2 = new User2Pojo(9999);
        postMethod(object);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),"9999");
    }
    @Test
    public void post4(){
        User2Pojo object3 = new User2Pojo();
        object3.setEmail("samet@gmail.com");
        object3.setStatus("Inactive");
        object3.setName(true);
        object3.setGender("956");
        postMethod(object3);
    }



}


