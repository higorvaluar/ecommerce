package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TokenService tokenService;

    public String login(String email, String senha) {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null || !BCrypt.checkpw(senha, usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        if (!usuario.getPerfil().equals(Perfil.ADMIN)) {
            throw new RuntimeException("Perfil não autorizado");
        }

        return tokenService.generateJwt(usuario);
    }
}