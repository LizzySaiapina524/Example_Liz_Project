package autotests.RestAssured.Pet_Shop;

import Technical_classes.RestAssured.API_Test_Data;
//*import Technical_classes.RestAssured.API_data_matching_response_fields;
import Technical_classes.RestAssured.API_data_matching_response_fields;
import Technical_classes.RestAssured.API_schema_response_fields;
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

public class All_put_methods {
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
                .body(API_Test_Data.UpdatePet)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверяем что схема респонса отвечает заявленной
        API_schema_response_fields.required_fields get_response_body =
                response.as(API_schema_response_fields.required_fields.class);

        // Проверяем что в респонсе те самые значения полей, которые нужны
        API_data_matching_response_fields put_response_body =
                response.as(API_data_matching_response_fields.class);

        // Получить питомца по ID и проверить
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/33")
                .then()
                .statusCode(200)
                .body("id", equalTo(33))
                .body("petId", equalTo(0))
                .body("quantity", equalTo(0))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }

    // Update an existing pet
    @Test
    public void UpdateUserInfoRequest() {

        Response response = (Response) given()
                .contentType(JSON)
                .body(API_Test_Data.UpdateUser)
                .when()
                .put("https://petstore.swagger.io/v2/user/cozymo")
                .then()
                .statusCode(200)
                .extract()
                .response();

        /// Проверяем что схема респонса отвечает заявленной
        API_schema_response_fields.TestDataPutResponse get_response_body =
                response.as(API_schema_response_fields.TestDataPutResponse.class);

        API_data_matching_response_fields.API_put_data_matching_response_fields putResponseBody =
                response.as(API_data_matching_response_fields.API_put_data_matching_response_fields.class);

        API_data_matching_response_fields.API_put_data_matching_response_fields.Put_user_checking_fields_value(putResponseBody);

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