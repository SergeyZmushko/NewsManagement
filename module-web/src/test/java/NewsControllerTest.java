import com.mjc.school.service.dto.CreateNewsDtoRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewsControllerTest extends FunctionalTest{
//
//    private final String REQUEST_MAPPING_URI = "/news";
//
//    @Test
//    @Order(1)
//    public void pingTest() {
//        given().when().get(REQUEST_MAPPING_URI).then().statusCode(200);
//    }
//
//    @Test
//    @Order(2)
//    public void invalidNewsId() {
//        given().when().get(REQUEST_MAPPING_URI + "/999")
//                .then().statusCode(404);
//    }
//
//    @Test
//    @Order(3)
//    public void addNewsToDb() {
//        List<String> tags = List.of("gossips", "music");
//        List<Long> commentsIds = new ArrayList<>();
//        CreateNewsDtoRequest createNewsDtoRequest = new CreateNewsDtoRequest("Seven hills", "funtastic news", "Arthur King", tags, commentsIds);
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).request().
//                        body(createNewsDtoRequest).
//                        post(REQUEST_MAPPING_URI);
//        System.out.println(myResponse.asString());
////
////        given()
////                .contentType(ContentType.JSON)
////                .body(createNewsDtoRequest)
////                .when().post(REQUEST_MAPPING_URI).then()
////                .body("title",equalTo("Seven hills"))
////                .body("content",equalTo("funtastic news"))
////                .statusCode(201);
//
////        newsID = myResponse.then().contentType(ContentType.JSON).extract().path("id");
////        System.out.println(newsID);
////        assertEquals(201, myResponse.getStatusCode());
////        assertEquals(, myResponse.then().extract().path("content"));
////        assertEquals("Seven hills", myResponse.then().extract().path("title"));
//    }
//
////    @Order(4)
////    @Test
////    public void verifyNameStructure() {
////        get(REQUEST_MAPPING_URI + "/" + 1)
////                .then()
////                .statusCode(200)
////                .assertThat()
////                .body("id", equalTo(1L))
////                .body("content", hasSize(greaterThan(3)));
////    }
//
//    @Order(5)
//    @Test
//    public void testUpdateMethod() {
//        Map<String, String> comment = new HashMap<>();
//        comment.put("content", "sport it's very good");
//        comment.put("newsId", "2");
//
//        Response myResponse =
//                given().
//                        when().contentType(ContentType.JSON).
//                        body(comment).
//                        patch(REQUEST_MAPPING_URI + "/" + 2);
//
//        assertEquals(200, myResponse.getStatusCode());
//        assertEquals("sport it's very good", myResponse.then().extract().path("content"));
//    }
//
//    @Order(6)
//    @Test
//    public void testDeleteMethod() {
//        given().when().delete(REQUEST_MAPPING_URI + "/" + 2).then()
//                .statusCode(204);
//    }
}
