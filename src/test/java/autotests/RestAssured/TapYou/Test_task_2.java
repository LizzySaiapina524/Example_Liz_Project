package autotests.RestAssured.TapYou;

import Technical_classes.RestAssured.Test_task_responses;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;


public class Test_task_2 {

    private Test_task_responses.GetUserInfo response;

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

    //Позитивный кейс
    @Test
    public void ExistingId() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/33")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUserInfo body =
                response.as(Test_task_responses.GetUserInfo.class);

        // Проверка, что значения корректны
        response.then()
                .body("isSuccess", equalTo(true))
                .body("errorCode", equalTo(0))
                .body("errorMessage", nullValue())
                .body("user.id", equalTo(33))
                .body("user.name", equalTo("Jesus"))
                .body("user.gender", equalTo("male"))
                .body("user.age", equalTo(33))
                .body("user.city", equalTo("Jerusalem"))
                .body("user.registrationDate", equalTo("0000-01-01T00:00:00"));
    }

    //Негативные кейсы

    @Test
    public void NotExistingId() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/0000000")
                .then()
                .statusCode(500)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as(Test_task_responses.IncorrectResponseScheme.class);

        // Проверка, что значения корректны
        response.then()
                .body("status", equalTo(500))
                .body("error", equalTo("Internal Server Error"))
                .body("message", equalTo("No message available"))
                .body("path", equalTo("/api/test/user/0000000"));
    }

    @Test
    public void StringInsteadIntId() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/abc")
                .then()
                .statusCode(400)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUserInfo body =
                response.as((Type) Test_task_responses.GetUserInfo.class);

        // Проверка, что значения корректны
        response.then()
                .body("isSuccess", equalTo(false))
                .body("errorCode", equalTo(400))
                .body("errorMessage", equalTo("NumberFormatException: For input string: \"abc\""))
                .body("user", nullValue());
    }

    @Test
    public void NULLId() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/NULL")
                .then()
                .statusCode(400)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUserInfo body =
                response.as((Type) Test_task_responses.GetUserInfo.class);

        // Проверка, что значения корректны
        response.then()
                .body("isSuccess", equalTo(false))
                .body("errorCode", equalTo(400))
                .body("errorMessage", equalTo("NumberFormatException: For input string: \"NULL\""))
                .body("user", nullValue());
    }

    @Test
    public void EmptyIdValue() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/")
                .then()
                .statusCode(404)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as((Type) Test_task_responses.IncorrectResponseScheme.class);

        // Проверка, что значения корректны
        response.then()
                .body("status", equalTo(404))
                .body("error", equalTo("Not Found"))
                .body("message", equalTo("No message available"))
                .body("path", equalTo("/api/test/user/"));
    }

    @Test
    public void EmptySpaceIdValue() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/ 33")
                .then()
                .statusCode(400)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUserInfo body =
                response.as((Type) Test_task_responses.GetUserInfo.class);

        // Проверка, что значения корректны
        response.then()
                .body("isSuccess", equalTo(false))
                .body("errorCode", equalTo(400))
                .body("errorMessage", equalTo("NumberFormatException: For input string: \" 33\""))
                .body("user", nullValue());
    }

    @Test
    public void NegativeIdValue() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/-33")
                .then()
                .statusCode(200)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.GetUserInfo body =
                response.as((Type) Test_task_responses.GetUserInfo.class);

        // Проверка, что значения корректны
        response.then()
                .body("isSuccess", equalTo(true))
                .body("errorCode", equalTo(0))
                .body("errorMessage", nullValue())
                .body("user", nullValue());
    }

    @Test
    public void ZeroIdValue() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/test/user/0")
                .then()
                .statusCode(500)
                .extract()
                .response(); // Сохраняем результат в переменную

        // Проверка на то, что присутствуют все необходимые поля
        Test_task_responses.IncorrectResponseScheme body =
                response.as((Type) Test_task_responses.IncorrectResponseScheme.class);

        // Проверка, что значения корректны
        response.then()
                .body("status", equalTo(500))
                .body("error", equalTo("Internal Server Error"))
                .body("message", equalTo("No message available"))
                .body("path", equalTo("/api/test/user/0"));
    }
}
