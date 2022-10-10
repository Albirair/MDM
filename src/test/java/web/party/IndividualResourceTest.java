package web.party;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonArray;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import java.io.File;
import javax.ws.rs.core.MediaType;

@QuarkusTest
@TestHTTPEndpoint(IndividualResource.class)
public class IndividualResourceTest {

    @Test
    public void list() {
        String result = given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .asString();
        System.out.println(result);
    }

    @Test
    public void create() {
        String result = given()
                .when()
                .body(new File("src/main/resources/individual.json"))
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .post()
                .then()
                .statusCode(201)
                .extract()
                .asString();
        System.out.println(result);
    }

    @Test
    public void patch() {
        JsonArray individuals = new JsonArray(given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .asString());
        if (!individuals.isEmpty()) {
            long id = individuals.getJsonObject(0).getLong("id");
            String result = given()
                    .when()
                    .body(new File("src/main/resources/patch.json"))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .patch(String.valueOf(id))
                    .then()
                    .statusCode(200)
                    .extract()
                    .asString();
            System.out.println("patched individual with id " + id + " and became\n" + result);
        } else
            System.out.println("no individual to patch");
    }

    @Test
    public void delete() {
        JsonArray individuals = new JsonArray(given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .asString());
        if (!individuals.isEmpty()) {
            long id = individuals.getJsonObject(0).getLong("id");
            given()
                    .when()
                    .delete(String.valueOf(id))
                    .then()
                    .statusCode(204);
            System.out.println("deleted individual with id " + id);
        }
    }
}