package autotests.restassured.petshop;

import technicalclasses.RestAssured.APITestData;
import technicalclasses.RestAssured.ResponseValueMatching;
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

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;

public class PutMethods {
    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webDrivers/chromedriver.exe");
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification requestSpec = new RequestSpecBuilder() // Спецификация запросов, участвующая во взаимодействии
                .setAccept(JSON)
                .setContentType(JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());                //логируем ошибку
    }

    // Update an existing pet
    @Test
    public void UpdatePetRequest() {

        Response response = (Response) given()
                .contentType(JSON)
                .body(APITestData.UpdatePet)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaPetResponse put_schema_response_body =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching put_values_response_body =
                response.as(ResponseValueMatching.class);

        //Get Pet by ID
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/33")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaPetResponse get_schema_response_body =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching get_values_response_body =
                response.as(ResponseValueMatching.class);
    }

    //Update an existing user
    @Test
    public void UpdateUserInfoRequest() {

        Response response = (Response) given()
                .contentType(JSON)
                .body(APITestData.UpdateUser)
                .when()
                .put("https://petstore.swagger.io/v2/user/cozymo")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserPutResponse put_response_schema =
                response.as(ResponseSchemaMatching.UserPutResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.PutUserDataMatching put_response_value =
                response.as(ResponseValueMatching.PutUserDataMatching.class);

        //Get User by ID
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/cozymo")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserGetResponse get_response_schema =
                response.as(ResponseSchemaMatching.UserGetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.UserDataMatching get_response_value =
                response.as(ResponseValueMatching.UserDataMatching.class);
    }
}