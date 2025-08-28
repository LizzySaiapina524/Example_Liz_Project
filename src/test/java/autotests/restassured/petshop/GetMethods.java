package autotests.restassured.petshop;

import technicalclasses.RestAssured.ResponseValueMatching;
import technicalclasses.RestAssured.ResponseSchemaMatching;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class GetMethods {


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

    //Find purchase order by ID
    @Test
    public void FindPurchaseOrder() {

        Response response = (Response) given()
                .when()
                .contentType(JSON)
                .get("https://petstore.swagger.io/v2/store/order/33")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaOrderResponse get_response_body =
                response.as(ResponseSchemaMatching.SchemaOrderResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.GetOrderDataMatching test_data_get_response_body =
                response.as(ResponseValueMatching.GetOrderDataMatching.class);

    }

    // Find pet by ID
    @Test
    public void FindPetById() {
        Response response = (Response) given()
                .when()
                //.head("API Key", "special-key") // Способ авторизации
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/12")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.SchemaPetResponse get_response_body =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.GetPetDataMatching test_data_get_response_body =
                response.as(ResponseValueMatching.GetPetDataMatching.class);

    }
}
