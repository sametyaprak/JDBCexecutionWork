package tests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.UserPojo;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static io.restassured.RestAssured.*;

public class US_02_Test {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";

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

}
