package autotests.RestAssured.Pet_Shop;

import Technical_classes.RestAssured.API_schema_response_fields;
import Technical_classes.RestAssured.API_Test_Data;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class All_types_of_requests {


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

    //GET
    @Test
    public void Get() {

        RestAssured.given()
                .when()
                .contentType(JSON)
                .get("https://petstore.swagger.io/v2/store/order/10")
                .then()                                                             // проверки
                .statusCode(200)
                .extract()
                .body()

                //Проверка на то, что присутствуют все необходимые поля
                .as(API_schema_response_fields.GetResponse.class);

        RestAssured.given()
                .when()
                .get("https://petstore.swagger.io/v2/store/order/10")
                .then()
                .assertThat()
                .statusCode(200)
                .and()

                // Проверка на то, что запрос возвращает те значения полей, которые задумано
                .body("id",is(10)
                        ,"petId",is(0)
                        ,"quantity",is(0)
                        ,"shipDate",is("2023-03-27T02:14:59.643+0000")
                        ,"status",is("placed")
                        ,"complete",is(true));
    }

    // POST
    @Test
    public void Post() {

        //Добавить нового питомца
        RestAssured.given()
                .contentType(JSON)
                .body(API_Test_Data.AddNewPet)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);

        // Получить питомца по ID и проверить
        given()
                .accept(JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/9999")
                .then()
                .statusCode(200)
                .body("id", equalTo(9999))
                .body("name", equalTo("Scratchy Scratch"))
                .body("status", equalTo("available"));

    }

}
