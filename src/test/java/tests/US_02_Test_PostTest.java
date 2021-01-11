package tests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.UserPojo;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class US_02_Test_PostTest {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    Map<String,Object> dataSendMap = new HashMap<>();

    @Test
    public void tc0201(){
        UserPojo userPojo = new UserPojo(ReusableMethods.randomString(5),
                                    ReusableMethods.randomString(5)+"@gmail.com",
                                "Male", "Active");
        response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(userPojo).
                    when().
                    post(endPoint);
        response.prettyPrint();
    }
    @Test
    public void tc0202(){
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        dataSendMap.put("gender","Female");
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data.field[0]"),"name");
        Assert.assertEquals(json.getString("data.message[0]"),"can't be blank");
    }
    @Test
    public void tc0203(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("gender","Female");
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data.field[0]"),"email");
        Assert.assertEquals(json.getString("data.message[0]"),"can't be blank");
    }
    @Test
    public void tc0204(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data.field[0]"),"gender");
        Assert.assertEquals(json.getString("data.message[0]"),"can't be blank");
    }
    @Test
    public void tc0205(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        dataSendMap.put("gender","Female");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data.field[0]"),"status");
        Assert.assertEquals(json.getString("data.message[0]"),"can't be blank");
    }
    @Test
    public void tc0206(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
//        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
//        dataSendMap.put("gender","Female");
//        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        List<String> fieldList = json.getList("data.field");
        List<String> messegeList = json.getList("data.message");
        Assert.assertTrue(fieldList.contains("email"));
        Assert.assertTrue(fieldList.contains("gender"));
        Assert.assertTrue(fieldList.contains("status"));
        for(String w : messegeList){
            Assert.assertTrue(w.equals("can't be blank"));
        }
    }
    @Test
    public void tc0207(){
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        List<String> fieldList = json.getList("data.field");
        List<String> messegeList = json.getList("data.message");
        Assert.assertTrue(fieldList.contains("name"));
        Assert.assertTrue(fieldList.contains("gender"));
        Assert.assertTrue(fieldList.contains("status"));
        for(String w : messegeList){
            Assert.assertTrue(w.equals("can't be blank"));
        }
    }
    @Test
    public void tc0208(){
        dataSendMap.put("gender","Female");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        List<String> fieldList = json.getList("data.field");
        List<String> messegeList = json.getList("data.message");
        Assert.assertTrue(fieldList.contains("name"));
        Assert.assertTrue(fieldList.contains("email"));
        Assert.assertTrue(fieldList.contains("status"));
        for(String w : messegeList){
            Assert.assertTrue(w.equals("can't be blank"));
        }
    }
    @Test
    public void tc0209(){
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        List<String> fieldList = json.getList("data.field");
        List<String> messegeList = json.getList("data.message");
        Assert.assertTrue(fieldList.contains("name"));
        Assert.assertTrue(fieldList.contains("email"));
        Assert.assertTrue(fieldList.contains("gender"));
        for(String w : messegeList){
            Assert.assertTrue(w.equals("can't be blank"));
        }
    }
    @Test
    public void tc0210(){
        List<Object>allKindsOfData = new ArrayList<>();
        allKindsOfData.add(123);
        allKindsOfData.add("aliveli");
        allKindsOfData.add('h');
        allKindsOfData.add(12.956);
        for(int i=0;i<allKindsOfData.size();i++){
            dataSendMap.put("name",allKindsOfData.get(i));
            dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
            dataSendMap.put("gender","Female");
            dataSendMap.put("status","Active");
            response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(dataSendMap).
                    when().
                    post(endPoint);
            response.prettyPrint();
            JsonPath json = response.jsonPath();
            String field = json.getString("data.field");
            String message = json.getString("data.message");
            Assert.assertEquals(json.getString("data.name"),allKindsOfData.get(i).toString());
        }
    }
    @Test
    public void tc0211(){
        List<Object>allKindsOfWrongData = new ArrayList<>();
        allKindsOfWrongData.add(123);
        allKindsOfWrongData.add("aliveli");
        allKindsOfWrongData.add("aliveli@");
        allKindsOfWrongData.add('h');
        allKindsOfWrongData.add(12.956);
        allKindsOfWrongData.add(12+"aliveli");
        for(int i=0;i<allKindsOfWrongData.size();i++){
            dataSendMap.put("name",ReusableMethods.randomString(6));
            dataSendMap.put("email",allKindsOfWrongData.get(i));
            dataSendMap.put("gender","Female");
            dataSendMap.put("status","Active");
            response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(dataSendMap).
                    when().
                    post(endPoint);
            response.prettyPrint();
            JsonPath json = response.jsonPath();
            Assert.assertEquals(json.getString("data[0].field"),"email");
            Assert.assertEquals(json.getString("data[0].message"),"is invalid");
        }
    }
    @Test
    public void tc0212(){
        List<Object>allKindsOfData = new ArrayList<>();
        allKindsOfData.add(123);
        allKindsOfData.add("aliveli");
        allKindsOfData.add('h');
        allKindsOfData.add(12.956);
        allKindsOfData.add(12+"aliveli");
        for(int i=0;i<allKindsOfData.size();i++){
            dataSendMap.put("name",ReusableMethods.randomString(6));
            dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
            dataSendMap.put("gender",allKindsOfData.get(i));
            dataSendMap.put("status","Active");
            response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(dataSendMap).
                    when().
                    post(endPoint);
            response.prettyPrint();
            JsonPath json = response.jsonPath();
            Assert.assertEquals(json.getString("data[0].field"),"gender");
            Assert.assertEquals(json.getString("data[0].message"),"can be Male or Female");
        }
    }
    @Test
    public void tc0213(){
        List<Object>allKindsOfData = new ArrayList<>();
        allKindsOfData.add(123);
        allKindsOfData.add("aliveli");
        allKindsOfData.add('h');
        allKindsOfData.add(12.956);
        allKindsOfData.add(12+"aliveli");
        for(int i=0;i<allKindsOfData.size();i++){
            dataSendMap.put("name",ReusableMethods.randomString(6));
            dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
            dataSendMap.put("gender","Female");
            dataSendMap.put("status",allKindsOfData.get(i));
            response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(dataSendMap).
                    when().
                    post(endPoint);
            response.prettyPrint();
            JsonPath json = response.jsonPath();
            Assert.assertEquals(json.getString("data[0].field"),"status");
            Assert.assertEquals(json.getString("data[0].message"),"can be Active or Inactive");
        }
    }
    @Test
    public void tc0214(){
            dataSendMap.put("name",null);
            dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
            dataSendMap.put("gender","Female");
            dataSendMap.put("status","Active");
            response =  given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigReader.getProperty("token")).
                    body(dataSendMap).
                    when().
                    post(endPoint);
            response.prettyPrint();
            JsonPath json = response.jsonPath();
            Assert.assertEquals(json.getString("data[0].field"),"name");
            Assert.assertEquals(json.getString("data[0].message"),"can't be blank");
    }
    @Test
    public void tc0215(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("email",null);
        dataSendMap.put("gender","Female");
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data[0].field"),"email");
        Assert.assertEquals(json.getString("data[0].message"),"can't be blank");
    }
    @Test
    public void tc0216(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        dataSendMap.put("gender",null);
        dataSendMap.put("status","Active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data[0].field"),"gender");
        Assert.assertEquals(json.getString("data[0].message"),"can't be blank");
    }
    @Test
    public void tc0217(){
        dataSendMap.put("name",ReusableMethods.randomString(6));
        dataSendMap.put("email",ReusableMethods.randomString(6)+"@gmail.com");
        dataSendMap.put("gender","Male");
        dataSendMap.put("status",null);
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigReader.getProperty("token")).
                body(dataSendMap).
                when().
                post(endPoint);
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        Assert.assertEquals(json.getString("data[0].field"),"status");
        Assert.assertEquals(json.getString("data[0].message"),"can't be blank");
    }

}
