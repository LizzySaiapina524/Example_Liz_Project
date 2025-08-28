package autotests.restassured.petshop;

import io.restassured.http.ContentType;
import technicalclasses.RestAssured.APITestData;
import technicalclasses.RestAssured.ResponseSchemaMatching;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import technicalclasses.RestAssured.ResponseValueMatching;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostMethods {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webDrivers/chromedriver.exe");
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification requestSpec = new RequestSpecBuilder() // Specification
                .setAccept(JSON)
                .setContentType(JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    //Place an order for a pet
    @Test
    public void AddNewOrder() {

        //Add new order
        Response response = (Response) given()
                .contentType(JSON)
                .body(APITestData.AddNewOrder)
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaOrderResponse post_response_scheme =
                response.as(ResponseSchemaMatching.SchemaOrderResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.OrderDataMatching post_response_value =
                response.as(ResponseValueMatching.OrderDataMatching.class);


        //Get order by ID and check the changes
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/23")
                .then()
                .statusCode(200);

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaOrderResponse get_response_scheme =
                response.as(ResponseSchemaMatching.SchemaOrderResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.OrderDataMatching get_response_value =
                response.as(ResponseValueMatching.OrderDataMatching.class);

    }

    //Add new Pet
    @Test
    public void AddNewPet() {

        //Add New Pet
        Response response = (Response) given()
                .contentType(JSON)
                .body(APITestData.AddNewPet)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaPetResponse post_response_scheme =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.PostPetDataMatching post_response_value =
                response.as(ResponseValueMatching.PostPetDataMatching.class);

        //Get pet by ID and check the changes
        given()
                .when()
                //.head("API Key", "special-key") // Способ авторизации
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/12")
                .then()
                .statusCode(200);

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaPetResponse get_response_body =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.PostPetDataMatching test_data_get_response_body =
                response.as(ResponseValueMatching.PostPetDataMatching.class);

    }
}