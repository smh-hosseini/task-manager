package org.iptiq.assignment.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.iptiq.assignment.domain.Process;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TaskManagerResourceTest {

    @Test
    public void testInit() {
        given()
          .when().post("/taskmanager/init/DEFAULT")
          .then()
             .statusCode(201);
    }

    @Test
    public void testInitFailure() {
        given()
                .when().post("/taskmanager/init/")
                .then()
                .statusCode(405);
    }

    @Test
    public void testAddProcess() {
        var process = Map.of("priority", "HIGH");
        given()
                .when().post("/taskmanager/init/DEFAULT")
                .then()
                .statusCode(201);
        given()
                .when().contentType(MediaType.APPLICATION_JSON)
                .body(process)
                .post("/taskmanager/")
                .then()
                .statusCode(200);
        given()
                .when().contentType(MediaType.APPLICATION_JSON)
                .body(process)
                .post("/taskmanager/")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/taskmanager/list")
                .then()
                .statusCode(200)
                .body("size()", is(2));

    }

    @Test
    public void testAddProcessFullCapacity() {
        var process = Map.of("priority", "HIGH");
        given()
                .when().post("/taskmanager/init/DEFAULT")
                .then()
                .statusCode(201);
        given()
                .when().contentType(MediaType.APPLICATION_JSON)
                .body(process)
                .post("/taskmanager/")
                .then()
                .statusCode(200);
        given()
                .when().contentType(MediaType.APPLICATION_JSON)
                .body(process)
                .post("/taskmanager/")
                .then()
                .statusCode(200);

        given()
                .when().contentType(MediaType.APPLICATION_JSON)
                .body(process)
                .post("/taskmanager/")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/taskmanager/list")
                .then()
                .statusCode(200)
                .body("size()", is(2));

    }

}