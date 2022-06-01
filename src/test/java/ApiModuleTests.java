import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;
import org.json.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ApiModuleTests {

    @Test
    public void PostTestingApi(){
        // https://petstore3.swagger.io/api/v3/pet

        //RestAssured.baseURI = "";
        RequestSpecification request = RestAssured.given();

        String jsonString = """
                {
                  "id": 10,
                  "name": "doggie",
                  "category": {
                    "id": 1,
                    "name": "Dogs"
                  },
                  "photoUrls": [
                    "string"
                  ],
                  "tags": [
                    {
                      "id": 0,
                      "name": "string"
                    }
                  ],
                  "status": "available"
                }""";
        JSONObject requestParams = new JSONObject(jsonString);

        request.header("Content-Type", "application/json"); // Add the Json to the body of the request
        request.body(requestParams); // Post the request and check the response

        Response response = request.post("https://petstore3.swagger.io/api/v3/pet/");
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
        System.out.println("The status received: " + response.statusLine());

        System.out.println("Trying get request");
        get("https://petstore3.swagger.io/api/v3/pet/10").then().body("id", equalTo(10));

        given().param(jsonString).
                when().
                post("https://petstore3.swagger.io/api/v3/pet/").
                then().
                body("id", equalTo("10"));


        delete("https://petstore3.swagger.io/api/v3/pet/10");
        get("https://petstore3.swagger.io/api/v3/pet/10").then().body("id", equalTo(10));



    }
}

/*



 */