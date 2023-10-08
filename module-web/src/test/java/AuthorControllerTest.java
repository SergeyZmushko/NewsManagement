import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorControllerTest extends FunctionalTest {
//
//    private final String REQUEST_MAPPING_URI = "/authors";
//    private static int authorID;
//
//    @Test
//    @Order(1)
//    public void pingTest() {
//        given().when().get(REQUEST_MAPPING_URI).then().statusCode(200);
//    }
//
//    @Test
//    @Order(2)
//    public void invalidAuthorId() {
//        given().when().get(REQUEST_MAPPING_URI + "/999")
//                .then().statusCode(404);
//    }
//
//    @Test
//    @Order(3)
//    public void addAuthorToDb() {
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "Siarhei Gav");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(author).
//                        post(REQUEST_MAPPING_URI);
//
//        authorID = myResponse.then().contentType(ContentType.JSON).extract().path("id");
//        assertEquals(201, myResponse.getStatusCode());
//        assertEquals("Siarhei Gav", myResponse.then().extract().path("name"));
//    }
//
//    @Order(4)
//    @Test
//    public void verifyNameStructured() {
//        get(REQUEST_MAPPING_URI + "/" + authorID)
//                .then()
//                .statusCode(200)
//                .assertThat()
//                .body("id", equalTo(authorID))
//                .body("name", equalTo("Siarhei Gav"));
//    }
//
//    @Order(5)
//    @Test
//    public void testUpdateMethod() {
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "Semen Smirnov");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(author).
//                        patch(REQUEST_MAPPING_URI + "/" + authorID);
//
//        assertEquals(200, myResponse.getStatusCode());
//        assertEquals("Semen Smirnov", myResponse.then().extract().path("name"));
//    }
//
//
//    @Order(6)
//    @Test
//    public void testDeleteMethod() {
//        given().when().delete(REQUEST_MAPPING_URI + "/" + authorID).then()
//                .statusCode(204);
//    }
//
//
//    @Order(7)
//    @Test
//    public void testReadByNewsId() {
//        Map<String, String> news = new HashMap<>();
//        news.put("title", "COMMERCE AND TRADE");
//        news.put("content", "A landlord's heartwarming Christmas present.");
//        news.put("author", "Artyom Eryomin");
//
//        given().when()
//                .contentType(ContentType.JSON)
//                .body(news)
//                .post("/news");
//
//        given().when().get("/news/1/author")
//                .then()
//                .body("id", equalTo(2))
//                .body("name", equalTo("Artyom Eryomin"))
//                .statusCode(200);
//    }
}
