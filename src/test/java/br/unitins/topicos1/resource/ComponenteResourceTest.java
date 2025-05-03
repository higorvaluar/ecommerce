package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ComponenteRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ComponenteResourceTest{

    String tokenAdm = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluQGV4YW1wbGUuY29tIiwiZ3JvdXBzIjpbIkFETUlOIl0sImlhdCI6MTc0NjI1NDcwOSwiZXhwIjoxNzQ2ODU5NTA5LCJqdGkiOiJmMTc5MGE1Zi0yNzdlLTQzZjUtYjZhNi0wNDI4ZDRmNmM0MGUifQ.K8mGMUqnZGHmK-X24fgoSzkb5vgVgy7SLyX4PwSRrstvjgEmHUn55sz1cDNI8-xAxOsYSd6IV-YjoKKqdKFwRZbNUBN-9r_C3aXMolS4seW9E4Tw6wSQ6leI0nIcighp_MK6_9LKWcLw8aJid28SRl9oDzNlMX-s3qXuo7LeLrUdpvKw56O_sLgYPRYZW7JtQD-CBVP7K5V0Tc9uEkedh8ZYZGL-RzdnyOAcwA7Dj3ml0gTjos3WQZUI09wq95cGGgbv3ma1W9W22bF6EKDI2rAWL8KqORYlMYt42b0aXKgieNIYAG_EvJVJGdI3oMfNO2mcMTqo9D0MVaX_XQK_bA";

    @Test
    public void findAllTest() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/componentes") // Corrigido: removido /api
                .then()
                .statusCode(200)
                .body("size()", is(notNullValue()));
    }

    @Test
    public void createTest() {

        ComponenteRequestDTO componenteDTO = new ComponenteRequestDTO("CPU", "É o cérebro", 100.00, 10);

        given()
        .header("Authorization", "Bearer " + tokenAdm)
                .contentType(ContentType.JSON)
                .body(componenteDTO)
                .when()
                .post("/api/componentes")
                .then()
                .statusCode(201)
                .body("nome", equalTo("CPU"));

    }

    @Test
    public void updateTest() {
        ComponenteRequestDTO componenteDTO = new ComponenteRequestDTO("GPU", "É o cérebro", 100.00, 10);

        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .contentType(MediaType.APPLICATION_JSON)
                .body(componenteDTO)
                .when()
                .pathParam("id",1)
                .put("/api/componentes/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void findByIdTest() {

        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/componentes/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", is(1));

    }

    @Test
    public void deleteTest() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .pathParam("id", 1)
                .delete("/api/componentes/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void findByNomeTest() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/componentes/search/{nome}", "Resistor 10k")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Resistor 10k")); // depende do shape do JSON retornado
    }

}