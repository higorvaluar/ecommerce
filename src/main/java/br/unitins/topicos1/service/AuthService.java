package br.unitins.topicos1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.util.TestLogCollector;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TokenService tokenService;

    public String login(String email, String senha) {
        TestLogCollector.addLog("Tentando login para email: " + email);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);
        if (usuario == null) {
            TestLogCollector.addLog("Usuário não encontrado para email: " + email);
            throw new WebApplicationException("Email ou senha inválidos", Response.Status.UNAUTHORIZED);
        }
        TestLogCollector.addLog("Usuário encontrado: " + usuario.getEmail());
        try {
            boolean senhaValida = BCrypt.checkpw(senha.trim(), usuario.getSenha().trim());
            if (!senhaValida) {
                TestLogCollector.addLog("Senha inválida para email: " + email);
                throw new WebApplicationException("Email ou senha inválidos", Response.Status.UNAUTHORIZED);
            }
            TestLogCollector.addLog("Senha válida, gerando token...");
            String token = tokenService.generateJwt(usuario);
            TestLogCollector.addLog("Token gerado: " + token);
            return token;
        } catch (Exception e) {
            TestLogCollector.addLog("Erro ao processar login: " + e.getMessage());
            throw new WebApplicationException("Erro interno no login", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}