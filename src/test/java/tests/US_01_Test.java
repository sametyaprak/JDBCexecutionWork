package tests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class US_01_Test {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";

    @Test
    public void tc0101(){
        response = given().accept(ContentType.JSON).auth().oauth2(ConfigReader.getProperty("token")).when().get(endPoint);
        response.prettyPrint();
    }
}
