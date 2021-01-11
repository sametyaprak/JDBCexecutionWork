package tests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ReusableMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class US_03_Test_PutTest {
    Response response;
    Map<String,Object> myPutData = new HashMap<>();
    String endpoint = "https://gorest.co.in/public-api/users/12";
    List<Object> allKindsOfWrongData = new ArrayList<>();
    JsonPath jsonPath;
    public void putResponse(Map data){
        response = given().
                contentType(ContentType.JSON).
                auth().
                oauth2(ConfigReader.getProperty("token")).
                body(data).
                when().
                put(endpoint);
        response.prettyPrint();
    }
    @AfterMethod
    public void fixAllDataAfterTest(){
        myPutData.put("name", ReusableMethods.randomString(6));
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        myPutData.put("gender","Female");
        myPutData.put("status","Active");
        putResponse(myPutData);
    }
    @Test
    public void TC0301(){
        myPutData.put("name", ReusableMethods.randomString(6));
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        myPutData.put("gender","Female");
        myPutData.put("status","Active");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),myPutData.get("name").toString());
        Assert.assertEquals(jsonPath.getString("data.email"),myPutData.get("email").toString());
        Assert.assertEquals(jsonPath.getString("data.gender"),myPutData.get("gender").toString());
        Assert.assertEquals(jsonPath.getString("data.status"),myPutData.get("status").toString());
    }
    @Test
    public void TC0302(){
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        myPutData.put("gender","Female");
        myPutData.put("status","Active");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.email"),myPutData.get("email").toString());
        Assert.assertEquals(jsonPath.getString("data.gender"),myPutData.get("gender").toString());
        Assert.assertEquals(jsonPath.getString("data.status"),myPutData.get("status").toString());
    }
    @Test
    public void TC0303(){
        myPutData.put("name", ReusableMethods.randomString(6));
        myPutData.put("gender","Female");
        myPutData.put("status","Active");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),myPutData.get("name").toString());
        Assert.assertEquals(jsonPath.getString("data.gender"),myPutData.get("gender").toString());
        Assert.assertEquals(jsonPath.getString("data.status"),myPutData.get("status").toString());
    }
    @Test
    public void TC0304(){
        myPutData.put("name", ReusableMethods.randomString(6));
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        myPutData.put("status","Active");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),myPutData.get("name").toString());
        Assert.assertEquals(jsonPath.getString("data.email"),myPutData.get("email").toString());

        Assert.assertEquals(jsonPath.getString("data.status"),myPutData.get("status").toString());
    }
    @Test
    public void TC0305(){
        myPutData.put("name", ReusableMethods.randomString(6));
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        myPutData.put("gender","Female");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),myPutData.get("name").toString());
        Assert.assertEquals(jsonPath.getString("data.email"),myPutData.get("email").toString());
        Assert.assertEquals(jsonPath.getString("data.gender"),myPutData.get("gender").toString());
    }
    @Test
    public void TC0306(){
        myPutData.put("name", ReusableMethods.randomString(6));
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.name"),myPutData.get("name").toString());
    }
    @Test
    public void TC0307(){
        myPutData.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.email"),myPutData.get("email").toString());
    }
    @Test
    public void TC0308(){
        myPutData.put("gender","Female");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.gender"),myPutData.get("gender").toString());
    }
    @Test
    public void TC0309(){
        myPutData.put("status","Active");
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.status"),myPutData.get("status").toString());
    }
    @Test
    public void TC0310(){
        allKindsOfWrongData.add(123);
        allKindsOfWrongData.add("aliveli");
        allKindsOfWrongData.add("aliveli@");
        allKindsOfWrongData.add('h');
        allKindsOfWrongData.add(12.956);
        allKindsOfWrongData.add(12+"aliveli");
        for(int i=0;i<allKindsOfWrongData.size();i++){
            myPutData.put("name",allKindsOfWrongData.get(i));
            putResponse(myPutData);
            jsonPath = response.jsonPath();
            Assert.assertEquals(myPutData.get("name").toString(),jsonPath.getString("data.name"));
        }
    }
    @Test
    public void TC0311(){
        allKindsOfWrongData.add(123);
        allKindsOfWrongData.add("aliveli");
        allKindsOfWrongData.add("aliveli@");
        allKindsOfWrongData.add('h');
        allKindsOfWrongData.add(12.956);
        allKindsOfWrongData.add(12+"aliveli");
        for(int i=0;i<allKindsOfWrongData.size();i++){
            myPutData.put("email",allKindsOfWrongData.get(i));
            putResponse(myPutData);
            jsonPath = response.jsonPath();
            Assert.assertEquals(myPutData.get("email").toString(),jsonPath.getString("data.email"));
        }
    }
    @Test
    public void TC0312(){
        allKindsOfWrongData.add(123);
        allKindsOfWrongData.add("aliveli");
        allKindsOfWrongData.add("aliveli@");
        allKindsOfWrongData.add('h');
        allKindsOfWrongData.add(12.956);
        allKindsOfWrongData.add(12+"aliveli");
        for(int i=0;i<allKindsOfWrongData.size();i++){
            myPutData.put("gender",allKindsOfWrongData.get(i));
            putResponse(myPutData);
            jsonPath = response.jsonPath();
            Assert.assertEquals("gender",jsonPath.getString("data.field[0]"));
            Assert.assertEquals("can be Male or Female",jsonPath.getString("data.message[0]"));
        }
    }
    @Test
    public void TC0313(){
        allKindsOfWrongData.add(123);
        allKindsOfWrongData.add("aliveli");
        allKindsOfWrongData.add("aliveli@");
        allKindsOfWrongData.add('h');
        allKindsOfWrongData.add(12.956);
        allKindsOfWrongData.add(12+"aliveli");
        for(int i=0;i<allKindsOfWrongData.size();i++){
            myPutData.put("status",allKindsOfWrongData.get(i));
            myPutData.put("gender","Male");
            putResponse(myPutData);
            jsonPath = response.jsonPath();
            Assert.assertEquals("status",jsonPath.getString("data.field[0]"));
            Assert.assertEquals("can be Active or Inactive",jsonPath.getString("data.message[0]"));
        }
    }
    @Test
    public void TC0314(){
        myPutData.put("name",null);
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals("name",jsonPath.getString("data.field[0]"));
        Assert.assertEquals("can't be blank",jsonPath.getString("data.message[0]"));
    }
    @Test
    public void TC0315(){
        myPutData.put("email",null);
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals("email",jsonPath.getString("data.field[0]"));
        Assert.assertEquals("can't be blank",jsonPath.getString("data.message[0]"));
    }
    @Test
    public void TC0316(){
        myPutData.put("gender",null);
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals("gender",jsonPath.getString("data.field[0]"));
        Assert.assertEquals("can't be blank",jsonPath.getString("data.message[0]"));
    }
    @Test
    public void TC0317(){
        myPutData.put("status",null);
        putResponse(myPutData);
        jsonPath = response.jsonPath();
        Assert.assertEquals("status",jsonPath.getString("data.field[0]"));
        Assert.assertEquals("can't be blank",jsonPath.getString("data.message[0]"));
    }
}
