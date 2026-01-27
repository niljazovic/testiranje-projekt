package hr.ferit.framework.api.tests;

import hr.ferit.framework.api.core.ApiBaseTest;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsApiTests extends ApiBaseTest {

    @Test
    public void get_post_by_id_should_return_200_and_expected_fields() {
        given()
                .when()
                .get("/posts/{id}", 1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", not(isEmptyOrNullString()));
    }

    @DataProvider(name = "postIds")
    public Object[][] postIds() {
        return new Object[][]{
                {1}, {2}, {3}
        };
    }

    @Test(dataProvider = "postIds")
    public void get_post_dataProvider_should_return_200(int id) {
        int returnedId =
                given()
                        .when()
                        .get("/posts/{id}", id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("id");

        Assert.assertEquals(returnedId, id);
    }

    @Test
    public void create_post_should_return_201_and_return_body() {
        String payload = """
            {
              "title": "ferit testing",
              "body": "hello from rest-assured",
              "userId": 1
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("ferit testing"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    public void update_post_should_return_200_and_updated_title() {
        String payload = """
            {
              "id": 1,
              "title": "updated title",
              "body": "updated body",
              "userId": 1
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/posts/{id}", 1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("updated title"));
    }

    @Test
    public void delete_post_should_return_200_or_204() {
        int status =
                given()
                        .when()
                        .delete("/posts/{id}", 1)
                        .then()
                        .extract()
                        .statusCode();

        Assert.assertTrue(status == 200 || status == 204, "Unexpected status: " + status);
    }
}
