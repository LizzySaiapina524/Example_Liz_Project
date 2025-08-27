package autotests.RestAssured.TapYou;

import Technical_classes.RestAssured.Test_task_responses;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;


public class Test_task_1 {

    // Создаем c помощью testng спецификацию, чтоб применить ее в тестах. Тут URL объявлен и Content Type
    @BeforeClass
    static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webDrivers/chromedriver.exe");
        RestAssured.baseURI = "https://hr-challenge.dev.tapyou.com";

        RequestSpecification requestSpec = new RequestSpecBuilder() // Спецификация запросов, участвующая во взаимодействии
                .setAccept(JSON)
                .setContentType(JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());                //логируем ошибку
    }

    // Позитивный кейс
    @Test
    public void GetUsersId() {

        Response response = given()
                .queryParam("gender", "female")
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUsersIdResponse body =
                response.as(Test_task_responses.GetUsersIdResponse.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("isSuccess", equalTo(true))
                .body("errorCode", equalTo(0))
                .body("idList", hasItems(5, 15, 16, 300, 502, 503));
    }

    //Негативные кейсы
    @Test
    public void InvalidDataChecking() {
        Response response = given()
                .queryParam("gender", "abc")
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(500)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("status", is(500))
                .body("error", is("Internal Server Error"))
                .body("message", is("No enum constant com.coolrocket.app.api.test.qa.Gender.abc"))
                .body("path", is("/api/test/users"));
    }

    @Test
    public void EmptyGender() {
        Response response = given()
                .queryParam("gender", "")
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(500)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("status", is(500))
                .body("error", is("Internal Server Error"))
                .body("message", is("No enum constant com.coolrocket.app.api.test.qa.Gender."))
                .body("path", is("/api/test/users"));
    }

    @Test
    public void EmptyParameter() {
        Response response = given()
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(400)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("status", is(400))
                .body("error", is("Bad Request"))
                .body("message", is("Required String parameter 'gender' is not present"))
                .body("path", is("/api/test/users"));
    }


    @Test
    public void UpperCaseGender() {
        Response response = given()
                .queryParam("gender", "MALE")
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(500)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("status", is(500))
                .body("error", is("Internal Server Error"))
                .body("message", is("No enum constant com.coolrocket.app.api.test.qa.Gender.MALE"))
                .body("path", is("/api/test/users"));
    }

    @Test
    public void EmptySpaceForGender() {
        Response response = given()
                .queryParam("gender", " male")
                .when()
                .get("/api/test/users")
                .then()
                .statusCode(500)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка на то, что запрос возвращает те значения полей, которые задумано
        response.then()
                .body("status", is(500))
                .body("error", is("Internal Server Error"))
                .body("message", is("No enum constant com.coolrocket.app.api.test.qa.Gender. male"))
                .body("path", is("/api/test/users"));
    }
}


