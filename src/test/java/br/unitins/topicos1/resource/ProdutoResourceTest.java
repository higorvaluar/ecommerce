package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Produto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ProdutoResourceTest {

    String tokenAdm = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluQGV4YW1wbGUuY29tIiwiZ3JvdXBzIjpbIkFETUlOIl0sImlhdCI6MTc0NjI1NDcwOSwiZXhwIjoxNzQ2ODU5NTA5LCJqdGkiOiJmMTc5MGE1Zi0yNzdlLTQzZjUtYjZhNi0wNDI4ZDRmNmM0MGUifQ.K8mGMUqnZGHmK-X24fgoSzkb5vgVgy7SLyX4PwSRrstvjgEmHUn55sz1cDNI8-xAxOsYSd6IV-YjoKKqdKFwRZbNUBN-9r_C3aXMolS4seW9E4Tw6wSQ6leI0nIcighp_MK6_9LKWcLw8aJid28SRl9oDzNlMX-s3qXuo7LeLrUdpvKw56O_sLgYPRYZW7JtQD-CBVP7K5V0Tc9uEkedh8ZYZGL-RzdnyOAcwA7Dj3ml0gTjos3WQZUI09wq95cGGgbv3ma1W9W22bF6EKDI2rAWL8KqORYlMYt42b0aXKgieNIYAG_EvJVJGdI3oMfNO2mcMTqo9D0MVaX_XQK_bA";

    @Test
    void findAllTest() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/produtos") // Corrigido: removido /api
                .then()
                .statusCode(200)
                .body("size()", is(notNullValue()));
    }

    @Test
    void createTest() {

        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");
        ProdutoRequestDTO produtoRequestDTO = new ProdutoRequestDTO("Arduino Uno", "Automatiza coisas", 300.00, 5,
                categoria);

        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .contentType(MediaType.APPLICATION_JSON)
                .body(produtoRequestDTO)
                .when()
                .post("/api/produtos")
                .then()
                .statusCode(201)
                .body("nome", is("Arduino Uno"));

    }

    @Test
    void FindByIdTest() {
        given()
        .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/produtos/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    // TODO refazer
    @Test
    void testUpdate() {

        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");
        ProdutoRequestDTO produtoRequestDTO = new ProdutoRequestDTO("Arduino Uno", "Automatiza coisas", 300.00, 5,
                categoria);

        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .contentType(MediaType.APPLICATION_JSON)
                .body(produtoRequestDTO)
                .when()
                .pathParam("id",1)
                .put("/api/produtos/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    void testDelete() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .pathParam("id", 1)
                .delete("/api/produtos/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void findByNome(){
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/componentes/search/{nome}", "Arduino Uno")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Arduino Uno"));
    }
}