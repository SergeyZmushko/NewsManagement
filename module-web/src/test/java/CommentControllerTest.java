import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTest extends FunctionalTest {
//
//    private final String REQUEST_MAPPING_URI = "/comments";
//    private static int commentID;
//
//    @Test
//    @Order(1)
//    public void pingTest() {
//        given().when().get(REQUEST_MAPPING_URI).then().statusCode(200);
//    }
//
//    @Test
//    @Order(2)
//    public void invalidCommentId() {
//        given().when().get(REQUEST_MAPPING_URI + "/999")
//                .then().statusCode(404);
//    }
//
//    @Test
//    @Order(3)
//    public void addCommentToDb() {
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
//        Map<String, String> comments = new HashMap<>();
//        comments.put("content", "funtastic");
//        comments.put("newsId", "1");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(comments).
//                        post(REQUEST_MAPPING_URI);
//
//        commentID = myResponse.then().contentType(ContentType.JSON).extract().path("id");
//        assertEquals(201, myResponse.getStatusCode());
//        assertEquals("funtastic", myResponse.then().extract().path("content"));
//    }
//
//    @Order(4)
//    @Test
//    public void verifyNameStructured() {
//        get(REQUEST_MAPPING_URI + "/" + commentID)
//                .then()
//                .statusCode(200)
//                .assertThat()
//                .body("id", equalTo(commentID))
//                .body("content", equalTo("funtastic"));
//    }
//
//    @Order(5)
//    @Test
//    public void testUpdateMethod() {
//        Map<String, String> comment = new HashMap<>();
//        comment.put("content", "sport it's very good");
//        comment.put("newsId", "1");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(comment).
//                        patch(REQUEST_MAPPING_URI + "/" + commentID);
//
//        assertEquals(200, myResponse.getStatusCode());
//        assertEquals("sport it's very good", myResponse.then().extract().path("content"));
//    }
//
//    @Order(6)
//    @Test
//    public void testDeleteMethod() {
//        given().when().delete(REQUEST_MAPPING_URI + "/" + commentID).then()
//                .statusCode(204);
//    }
}
