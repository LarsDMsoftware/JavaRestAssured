import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiModuleTests {

    @Test
    public void PostTestingApi() throws IOException, ParseException {
        // https://petstore3.swagger.io/api/v3/pet
        RequestSpecification request = RestAssured.given();

        JSONParser parser = new JSONParser();
        JSONObject a = (JSONObject) parser.parse(new FileReader("src/test/java/pet.json"));

        request.header("Content-Type", "application/json"); // Add the Json to the body of the request
        request.body(a); // Post the request and check the response

        Response response = request.post("https://petstore3.swagger.io/api/v3/pet/");
        ResponseBody body = response.getBody();

        System.out.println(body.asString());
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Trying get request");

        get("https://petstore3.swagger.io/api/v3/pet/10").then().body("id", equalTo(10));

        given().param(a.toJSONString()).
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