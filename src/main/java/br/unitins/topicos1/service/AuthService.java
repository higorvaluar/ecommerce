package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TokenService tokenService;

    public String login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            System.out.println("Usuário não encontrado para o email: " + email);
            throw new WebApplicationException("Email ou senha inválidos", Response.Status.UNAUTHORIZED);
        }

        System.out.println("Senha fornecida: " + senha + "'");
        System.out.println("Senha armazenada: " + usuario.getSenha() + "'");
        boolean senhaValida = BCrypt.checkpw(senha.trim(), usuario.getSenha());
        System.out.println("Resultado da verificação de senha: " + senhaValida);

        if (!senhaValida) {
            throw new WebApplicationException("Email ou senha inválidos", Response.Status.UNAUTHORIZED);
        }

        // Removido temporariamente para testes
        // if (!usuario.getPerfil().equals(Perfil.ADMIN)) {
        //     throw new WebApplicationException("Perfil não autorizado", Response.Status.FORBIDDEN);
        // }

        String token = tokenService.generateJwt(usuario);
        System.out.println("Token gerado: " + token);
        return token;
    }
}