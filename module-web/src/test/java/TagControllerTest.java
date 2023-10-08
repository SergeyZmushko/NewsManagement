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
public class TagControllerTest extends FunctionalTest {
//
//    private final String REQUEST_MAPPING_URI = "/tags";
//    private static int tagID;
//
//    @Test
//    @Order(1)
//    public void pingTest() {
//        given().when().get(REQUEST_MAPPING_URI).then().statusCode(200);
//    }
//
//    @Test
//    @Order(2)
//    public void invalidTagId() {
//        given().when().get(REQUEST_MAPPING_URI + "/999")
//                .then().statusCode(404);
//    }
//
//    @Test
//    @Order(3)
//    public void addTagToDb() {
//        Map<String, String> tags = new HashMap<>();
//        tags.put("name", "funtastic");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(tags).
//                        post(REQUEST_MAPPING_URI);
//
//        tagID = myResponse.then().contentType(ContentType.JSON).extract().path("id");
//        assertEquals(201, myResponse.getStatusCode());
//        assertEquals("funtastic", myResponse.then().extract().path("name"));
//    }
//
//    @Order(4)
//    @Test
//    public void verifyNameStructured() {
//        get(REQUEST_MAPPING_URI + "/" + tagID)
//                .then()
//                .statusCode(200)
//                .assertThat()
//                .body("id", equalTo(tagID))
//                .body("name", equalTo("funtastic"));
//    }
//
//    @Order(5)
//    @Test
//    public void testUpdateMethod() {
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "sport");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(author).
//                        patch(REQUEST_MAPPING_URI + "/" + tagID);
//
//        assertEquals(200, myResponse.getStatusCode());
//        assertEquals("sport", myResponse.then().extract().path("name"));
//    }
//
//
//    @Order(6)
//    @Test
//    public void testDeleteMethod() {
//        given().when().delete(REQUEST_MAPPING_URI + "/" + tagID).then()
//                .statusCode(204);
//    }
}
