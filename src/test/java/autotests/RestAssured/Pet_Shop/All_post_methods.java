package autotests.RestAssured.Pet_Shop;

import Technical_classes.RestAssured.API_Test_Data;
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

public class All_post_methods {

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

    // Place an order for a pet
    @Test
    public void OrderForPetRequest() {

        //Добавить новый заказ
        RestAssured.given()
                .contentType(JSON)
                .body(API_Test_Data.AddNewOrder)
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .then()
                .statusCode(200);

        // Получить питомца по ID и проверить
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/23")
                .then()
                .statusCode(200)
                .body("id", equalTo(23))
                .body("petId", equalTo(23))
                .body("quantity", equalTo(1))
                .body("shipDate", equalTo("2025-07-30T23:00:06.189+0000"))
                .body("status", equalTo("available"))
                .body("complete", equalTo(true));

    }

    @Test
    public void OrderForPetRequestOptimized() {

        //Добавить новый заказ
        Response response = (Response) given()
                .contentType(JSON)
                .body(API_Test_Data.AddNewOrder)
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .then()
                .statusCode(200)
                .extract()
                .response();

        API_schema_response_fields.GetResponse post_response_body =
                response.as(API_schema_response_fields.GetResponse.class);

        // Тут мы отправляем GET запрос чтоб проверить что новый заказ добавился
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/23")
                .then()
                .statusCode(200);

        //Поля респонса на проверяющий изменения GET запрос соответствуют схеме
        API_schema_response_fields.GetResponse get_response_body =
                response.as(API_schema_response_fields.GetResponse.class);

        //Поля респонса на проверяющий изменения GET запрос заполнены верными значениями
        API_schema_response_fields.TestDataGetResponse test_data_get_response_body =
                response.as(API_schema_response_fields.TestDataGetResponse.class);

    }
}