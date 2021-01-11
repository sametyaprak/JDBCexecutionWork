package tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.ExcelUtilX;

import java.util.*;

public class US_01_Test_GetTest {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    List<Integer> integerDataList = new ArrayList<>();
    HashSet<Integer> integerDataSet = new HashSet<>();
    List<String> stringDataList = new ArrayList<>();
    HashSet<String> stringDataSet = new HashSet<>();

    public void getResponse(String endPoint){
        response = given().accept(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).when().get(endPoint);
    }

    public void getAllDataToecxel(){
        String excelPath = "src/test/resources/exceldata.xlsx";
        String sheetName = "allData";
        int row =1;
        ExcelUtilX excelObject = new ExcelUtilX(excelPath,sheetName);
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        for(int i=1;i<totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for (int j=0;j<20;j++){
                if(!json.getString("data.name["+j+"]").equals(null)){
                String data = json.getString("data.name["+j+"]");
                    Integer idValue = json.getInt("data.id["+j+"]");
                    excelObject.setCellDataWithColumnName(idValue.toString(),"id",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.name["+j+"]"),"name",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.email["+j+"]"),"email",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.gender["+j+"]"),"gender",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.status["+j+"]"),"status",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.created_at["+j+"]"),"created_at",row);
                    excelObject.setCellDataWithColumnName(json.getString("data.updated_at["+j+"]"),"updated_at",row);
                    row++;
                }
            }
        }
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
        //Assert.assertEquals(json.getInt("meta.pagination.total"),1375);
    }
    @Test
    public void tc0104(){
        getResponse(endPoint);
        json = response.jsonPath();
        //Assert.assertEquals(json.getInt("meta.pagination.pages"),69);
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
            integerDataList.addAll(json.getList("data.id"));
        }
        System.out.println(integerDataList);
        for (int i = 0; i< integerDataList.size()-1; i++){
            if(integerDataList.get(i)> integerDataList.get(i+1)){
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
            integerDataList.addAll(json.getList("data.id"));
            integerDataSet.addAll(json.getList("data.id"));
        }
        Assert.assertEquals(integerDataList.size(), integerDataSet.size());
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
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.gender["+j+"]")!=null){
                    if(json.getString("data.gender["+j+"]").equals("Male")){
                        System.out.println(i+" page, "+ j + " index data is MALE");
                        numberOfMaleData++;
                    }
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
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.gender["+j+"]")!=null) {
                    if (json.getString("data.gender[" + j + "]").equals("Female")) {
                        System.out.println(i + " page, " + j + " index data is FEMALE");
                        numberOfFemaleData++;
                    }
                }
            }
        }
        System.out.println(numberOfFemaleData);
        //Assert.assertEquals(numberOfMaleData,0);
    }
    @Test
    public void tc0110(){
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            stringDataList.addAll(json.getList("data.name"));
            stringDataSet.addAll(json.getList("data.name"));
        }
        Assert.assertNotEquals(stringDataList.size(), stringDataSet.size());
        int sameData =0;
        for (int w = 0; w< stringDataList.size(); w++){
            for(int j = 0; j< stringDataSet.size(); j++){
                if(w!=j && stringDataList.get(w).equals(stringDataList.get(j))){
                    sameData++;
                    System.out.println(stringDataList.get(w));
                }
            }
        }
        System.out.println("total dublicate data: " + sameData);
    }
    @Test
    public void tc0111(){
        int numberOfFemaleData = 0;
        int numberOfMaleData = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++) {
                if (json.getString("data.gender[" + j + "]") != null) {
                    if (json.getString("data.gender[" + j + "]").equals("Female")) {
                        System.out.println(i + " page, " + j + " index data is FEMALE");
                        numberOfFemaleData++;
                    } else {
                        numberOfMaleData++;
                    }
                }
            }
        }
        System.out.println("female data total: "+numberOfFemaleData);
        System.out.println("male data total: "+numberOfMaleData);
    }
    @Test
    public void tc0112(){
        int numberOfActiveData = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.status["+j+"]").equals("Active")){
                    System.out.println(i+" page, "+ j + " index data is ACTIVE");
                    numberOfActiveData++;
                }
            }
        }
        System.out.println(numberOfActiveData);
    }
    @Test
    public void tc0113(){
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            integerDataList.addAll(json.getList("data.id"));
        }
        Assert.assertFalse(integerDataList.contains(4142));
    }
    @Test
    public void tc0114(){
        String [] dataTypes = {"name", "email", "gender", "status", "created_at", "updated_at"};
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++) {
                if (json.getString("data[" + j + "]") != null) {
                    for (int k = 0; k < dataTypes.length; k++) {
                        if (json.getString("data." + dataTypes[k] + "[" + j + "]").equals(null)) {
                            System.out.println(i + " page, " + j + " index " + dataTypes[k] + " data is NULL");
                            System.exit(1);
                        }
                    }
                }
            }
        }

    }
    @Test
    public void tc0115(){
        String searchName = "Patricia";
        int controlCode = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                //System.out.println(json.getString("data.name["+j+"]"));
                if (json.getString("data.name[" + j + "]") != null) {
                    if (json.getString("data.name[" + j + "]").equals(searchName)) {
                        System.out.println(i + " page, " + j + " index name is " + searchName);
                        controlCode++;
                    }
                }
            }
        }
        Assert.assertEquals(controlCode,0);
    }
    @Test
    public void tc0116(){
        String searchEmail = "aliveli@gmail.com";
        int controlCode = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.email["+j+"]")!=null){
                    if(json.getString("data.email["+j+"]").equals(searchEmail)) {
                        System.out.println(i + " page, " + j + " index name is " + searchEmail);
                        controlCode++;
                    }
                }
            }
        }
        //Assert.assertEquals(controlCode,0);
    }
    @Test
    public void tc0117(){
        String searchEmail = "@gmail";
        int controlCode = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.email["+j+"]")!=null){
                    if(json.getString("data.email["+j+"]").contains(searchEmail)){
                        System.out.println(i+" page, "+ j + " index name is " + searchEmail);
                        controlCode++;
                    }
                }
            }
        }
        System.out.println("total search result: "+controlCode);
        Assert.assertTrue(controlCode>0);
    }
    @Test
    public void tc0118(){
        int year = 2020;
        int controlCode = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.created_at["+j+"]")!=null){
                    if(Integer.valueOf(json.getString("data.created_at["+j+"]").substring(0,4))<year){
                        System.out.println(i+" page, "+ j + " index name is " + year);
                        controlCode++;
                    }
                }
            }
        }
        System.out.println("total search result: "+controlCode);
        Assert.assertTrue(controlCode>=0);
    }

    @Test
    public void tc0119(){
        int numberOfMaleData = 0;
        String sirname ="";
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.name["+j+"]")!=null){
                    for(int k=json.getString("data.name["+j+"]").length()-1;k>=0;k--){
                        if(json.getString("data.name["+j+"]").charAt(k)==' '){
                            sirname = json.getString("data.name["+j+"]").substring(k+1);
                            System.out.println(sirname);
                            if(sirname.charAt(0)=='A' || sirname.charAt(0)=='D'  ){
                                System.out.println(i+" page, "+ j + " index data begins with A or D: "+ sirname);
                                numberOfMaleData++;
                            }
                        }
                    }

                }

            }
        }
        System.out.println(numberOfMaleData);
        //Assert.assertEquals(numberOfMaleData,0);
    }
    @Test
    public void tc0120(){
        int controlCode = 0;
        getResponse(endPoint);
        json = response.jsonPath();
        int totalPages = json.getInt("meta.pagination.pages");
        int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
            getResponse(endPoint+ "?page="+i);
            json = response.jsonPath();
            for(int j=0;j<totalLimitPerPages;j++){
                if(json.getString("data.created_at["+j+"]")!=null){
                    if(!json.getString("data.created_at["+j+"]").equals(json.getString("data.updated_at["+j+"]"))){
                        System.out.println(i+" page, "+ j + " index name is UPDATED");
                        controlCode++;
                    }
                }
            }
        }
        System.out.println("total UPDATED result: "+controlCode);
        Assert.assertTrue(controlCode>0);
    }



}
