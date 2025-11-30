package autotests.restassured.petshop;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import technicalclasses.RestAssured.APITestData;
import technicalclasses.RestAssured.ResponseSchemaMatching;
import technicalclasses.RestAssured.ResponseValueMatching;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DeleteMethods {
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

    @Test
    public void DeletePet() {

        //Add New Pet for checking Delete method
        Response response = (Response) given()
                .config(RestAssured.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 3000)      // 3 сек на подключение
                                .setParam("http.socket.timeout", 3000)          // 3 сек на ответ
                                .setParam("http.connection-manager.timeout", 3000))) // 3 сек на соединение из пула
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

        //Delete New Pet
        Response deleteResponse = given()
                .config(RestAssured.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 3000)      // 3 сек на подключение
                                .setParam("http.socket.timeout", 3000)          // 3 сек на ответ
                                .setParam("http.connection-manager.timeout", 3000))) // 3 сек на соединение из пула
                .when()
                .contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/pet/9999")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserPutResponse delete_response_scheme =
                deleteResponse.as(ResponseSchemaMatching.UserPutResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.DeletePetDataMatching delete_response_value =
                deleteResponse.as(ResponseValueMatching.DeletePetDataMatching.class);

        //Check Pet Absence
        Response getResponse = given()
                .config(RestAssured.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 3000)      // 3 сек на подключение
                                .setParam("http.socket.timeout", 3000)          // 3 сек на ответ
                                .setParam("http.connection-manager.timeout", 3000))) // 3 сек на соединение из пула
                .when()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/9999")
                .then()
                .statusCode(404)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserPutResponse get_response_schema =
                getResponse.as(ResponseSchemaMatching.UserPutResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.DeleteDataMatching get_response_value =
                getResponse.as(ResponseValueMatching.DeleteDataMatching.class);

    }

    @Test
    public void DeleteOrder() {

        //Add New Order for checking Delete method
        Response response = (Response) given()
                .contentType(JSON)
                .body(APITestData.AddNewOrderForDelete)
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

        //Delete New Order
        Response deleteResponse = given()
                .when()
                .contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/store/order/3")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserPutResponse delete_response_scheme =
                deleteResponse.as(ResponseSchemaMatching.UserPutResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.DeleteOrderDataMatching delete_response_value =
                deleteResponse.as(ResponseValueMatching.DeleteOrderDataMatching.class);

        //Check Order Absence
        Response getResponse = given()
                .when()
                .contentType(JSON)
                .get("https://petstore.swagger.io/v2/store/order/3")
                .then()
                .statusCode(404)
                .extract()
                .response();

        //The response fields correspond to the scheme
        ResponseSchemaMatching.UserPutResponse get_response_body =
                getResponse.as(ResponseSchemaMatching.UserPutResponse.class);

        //The response fields are filled with the correct values
        ResponseValueMatching.DeleteOrderDataMatching test_data_get_response_body =
                getResponse.as(ResponseValueMatching.DeleteOrderDataMatching.class);
    }
}
