package br.unitins.topicos1.resource;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import jakarta.transaction.Transactional;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.util.TestLogCollector;
import org.mindrot.jbcrypt.BCrypt;

import static io.restassured.RestAssured.given;

public abstract class BaseResourceTest {
    protected String token;

    @BeforeEach
    @Transactional
    public void setUp() {
        // Limpar banco
        Usuario.deleteAll();

        // Criar usuário admin
        Usuario admin = new Usuario();
        admin.setEmail("admin@email.com");
        admin.setNome("Higor");
        admin.setPerfil(Perfil.ADMIN);
        admin.setSenha(BCrypt.hashpw("Admin@2025", BCrypt.gensalt()));
        admin.persist();

        // Gerar token
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"admin@email.com\", \"senha\": \"Admin@2025\"}")
                .when()
                .post("/auth/login");
        TestLogCollector.addLog("Resposta do /auth/login: Status=" + response.getStatusCode() + ", Body=" + response.asString());
        token = response
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}