package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class UsuarioResourceTest {

    String tokenAdm = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImFkbWluQGV4YW1wbGUuY29tIiwiZ3JvdXBzIjpbIkFETUlOIl0sImlhdCI6MTc0NjI1NDcwOSwiZXhwIjoxNzQ2ODU5NTA5LCJqdGkiOiJmMTc5MGE1Zi0yNzdlLTQzZjUtYjZhNi0wNDI4ZDRmNmM0MGUifQ.K8mGMUqnZGHmK-X24fgoSzkb5vgVgy7SLyX4PwSRrstvjgEmHUn55sz1cDNI8-xAxOsYSd6IV-YjoKKqdKFwRZbNUBN-9r_C3aXMolS4seW9E4Tw6wSQ6leI0nIcighp_MK6_9LKWcLw8aJid28SRl9oDzNlMX-s3qXuo7LeLrUdpvKw56O_sLgYPRYZW7JtQD-CBVP7K5V0Tc9uEkedh8ZYZGL-RzdnyOAcwA7Dj3ml0gTjos3WQZUI09wq95cGGgbv3ma1W9W22bF6EKDI2rAWL8KqORYlMYt42b0aXKgieNIYAG_EvJVJGdI3oMfNO2mcMTqo9D0MVaX_XQK_bA";

    @Test
    void testGetAll() {
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .queryParam("page", 0)
                .queryParam("pageSize", 10)
                .when()
                .get("/api/usuarios")
                .then()
                .statusCode(200)
                .body("size()", is(notNullValue()));
    }

    @Test
    public void createTest(){
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO("teste@gmail.com", "teste", "1", "123");

        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .contentType(ContentType.JSON)
                .body(usuarioRequestDTO)
                .when()
                .post("/api/usuarios")
                .then()
                .statusCode(201)
                .body("senha", equalTo("123"));
    }

//    @Test
//    public void updateTest(){
//        given()
//                .header("Authorization", "Bearer " + tokenAdm)
//                .contentType(ContentType.JSON)
//                .pathParam("id",1)
//                .when()
//                .put("/api/usuarios/{id}")
//                .then()
//                .statusCode(204);
//    }

    @Test
    public void deleteTest(){
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .pathParam("id", 2)
                .delete("/api/usuarios/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void findByEmailTest(){
        given()
                .header("Authorization", "Bearer " + tokenAdm)
                .when()
                .get("/api/usuarios/email/admin@example.com")
                .then()
                .statusCode(200)
                .body("email", is("admin@example.com"));
    }

}