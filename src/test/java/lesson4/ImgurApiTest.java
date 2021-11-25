package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static lesson4.ImgurApiParams.TOKEN;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ImgurApiTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = ImgurApiParams.API_URL + "/" + ImgurApiParams.API_VERSION;
    }

    @DisplayName("Тест на получение базовой информации об аккаунте")
    @Test
    @Order(1)
    void testAccountBase() {
        String url = "account/" + "facebook526";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();


        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .expectBody("data.reputation", is(0))
                .expectStatusCode(200)
                .build();

        given().when()
                .spec(requestSpecification)
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }

    @DisplayName("Тест на получение информации о картинке")
    @Test
    @Order(2)
    void testImage() {
        String imageId = "zILgfOV";
        String url = "image/" + imageId;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .expectStatusCode(200)
                .build();

        given().when()
                .spec(requestSpecification)
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }

    @DisplayName("Тест обновления информации о картинке")
    @Test
    @Order(3)
    void testUpdateImageInfo() {
        String imageHash = "zILgfOV";
        String url = "image/" + imageHash;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addFormParam("title", "Cat")
                .addFormParam("description", "Cat")
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("success", is(true))
                .expectBody("data", is(true))
                .expectStatusCode(200)
                .build();

        given().when()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .spec(requestSpecification)
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .post(url);
    }


    @DisplayName("Тест на проверку типа картинки")
    @Test
    @Order(4)
    void testDataType() {
        String imageId = "zILgfOV";
        String url = "image/" + imageId;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("data.type", is("image/jpeg"))
                .expectStatusCode(200)
                .build();

        given().when()
                .spec(requestSpecification)
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }

    @DisplayName("Тест на проверку описания картинки")
    @Test
    @Order(5)
    void testDataDescription() {
        String imageId = "zILgfOV";
        String url = "image/" + imageId;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("data.description", is("Cat"))
                .expectBody("data.title", is("Cat"))
                .expectStatusCode(200)
                .build();

        given().when()
                .spec(requestSpecification)
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }

    @DisplayName("Тест на проверку значения datetime")
    @Test
    @Order(6)
    void testDatetime() {
        String imageId = "zILgfOV";
        String url = "image/" + imageId;

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", TOKEN)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectBody("data.datetime", is(1636177427))
                .expectStatusCode(200)
                .build();

        given().when()
                .spec(requestSpecification)
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .log()
                .all()
                .spec(responseSpecification)
                .when()
                .get(url);
    }



}
