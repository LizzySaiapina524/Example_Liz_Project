package autotests.restassured.petshop;

import technicalclasses.RestAssured.APITestData;
//*import Technical_classes.RestAssured.API_data_matching_response_fields;
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
import static org.testng.Assert.assertEquals;

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

        // Проверяем что схема респонса отвечает заявленной
        ResponseSchemaMatching.SchemaPetResponse put_schema_response_body =
                response.as(ResponseSchemaMatching.SchemaPetResponse.class);

        // Проверяем что в респонсе те самые значения полей, которые нужны
        ResponseValueMatching put_values_response_body =
                response.as(ResponseValueMatching.class);

        // Получить питомца по ID и проверить
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/33")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверяем что в респонсе те самые значения полей, которые нужны
        ResponseValueMatching get_value_response_body =
                response.as(ResponseValueMatching.class);

    }

    // Update an existing user
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

        // Проверяем что схема респонса отвечает заявленной
        ResponseSchemaMatching.TestDataPutResponse get_response_body =
                response.as(ResponseSchemaMatching.TestDataPutResponse.class);

        // Проверяем что в респонсе те самые значения полей, которые нужны
        ResponseValueMatching.API_put_data_matching_response_fields putResponseBody =
                response.as(ResponseValueMatching.API_put_data_matching_response_fields.class);

        ResponseValueMatching.API_put_data_matching_response_fields.Put_user_checking_fields_value(putResponseBody);

        // Получить юзера по ID и проверить
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/cozymo")
                .then()
                .statusCode(200)
                .body("id", equalTo(222))
                .body("username", equalTo("cozymo"))
                .body("firstName", equalTo("Cozy"))
                .body("lastName", equalTo("Mo"))
                .body("email", equalTo("cozymore@gmail.com"))
                .body("password", equalTo("cozymo25"))
                .body("phone", equalTo("5555555"))
                .body("userStatus", equalTo(1));
    }
}