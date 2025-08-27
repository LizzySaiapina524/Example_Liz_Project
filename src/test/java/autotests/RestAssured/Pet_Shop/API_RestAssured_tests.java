package autotests.RestAssured.Pet_Shop;

import Technical_classes.RestAssured.API_schema_response_fields;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;


public class API_RestAssured_tests {


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

    // Проверяем что все необходимые поля есть в респонсе
    @Test
    public void GetApiMethod() {
        given()
                .when()
                //.head("API Key", "special-key") // Способ авторизации
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/12")
                .then()
                .statusCode(200)                                    //логируем ошибку, если код ошибки не 200, то тест упадёт
                .extract()
                .body()
                .as(API_schema_response_fields.required_fields.class);

    }

    // Проверяем что значение в поле соответствует заданному
    @Test
    public void GetRestAssuredTest() {
        RestAssured.
                when()
                .get("https://petstore.swagger.io/v2/pet/12")
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("id", is(12),"status",is("available"));
    }
}
