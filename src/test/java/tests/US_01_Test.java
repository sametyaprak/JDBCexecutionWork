package tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import utilities.ConfigReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class US_01_Test {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    List<String> dataString = new ArrayList<>();
    List<Integer> dataInteger = new ArrayList<>();
    HashSet<Integer> dataIntegerSet = new HashSet<>();

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
        Assert.assertEquals(json.getInt("meta.pagination.total"),1375);
    }
    @Test
    public void tc0104(){
        getResponse(endPoint);
        json = response.jsonPath();
        Assert.assertEquals(json.getInt("meta.pagination.pages"),69);
    }
    @Test
    public void tc0105(){
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int sum =0;
        for(int i=1;i<=totalPages;i++){
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
    public void test106(){
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            dataInteger.addAll(json.getList("data.id"));
            dataIntegerSet.addAll(json.getList("data.id"));
        }
        Assert.assertEquals(dataInteger.size(),dataIntegerSet.size());
    }


    @Test
    public void tc0107(){
        int numberOfNullData = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.name["+j+"]")==null){
                    System.out.println(i+" page, "+ j + "index name is NULL");
                    numberOfNullData++;
                }
            }
        }
        System.out.println(numberOfNullData);
        Assert.assertEquals(numberOfNullData,0);
    }
    @Test
    public void tc0108(){
        int numberOfMaleData = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.gender["+j+"]").equals("Male")){
                    System.out.println(i+" page, "+ j + " index data is MALE");
                    numberOfMaleData++;
                }
            }
        }
        System.out.println(numberOfMaleData);
        //Assert.assertEquals(numberOfMaleData,0);
    }
    @Test
    public void tc0109(){
        int numberOfFemaleData = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.gender["+j+"]").equals("Female")){
                    System.out.println(i+" page, "+ j + " index data is FEMALE");
                    numberOfFemaleData++;
                }
            }
        }
        System.out.println(numberOfFemaleData);
        //Assert.assertEquals(numberOfMaleData,0);
    }
}
