package br.unitins.topicos1.service;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenService {

    public String generateJwt(Usuario usuario) {
        Set<String> roles = new HashSet<>();
        roles.add(usuario.getPerfil().toString());

        return Jwt.issuer("unitins-jwt")
                .subject(usuario.getEmail())
                .groups(roles)
                .expiresIn(Duration.ofHours(1))
                .sign();
    }
}