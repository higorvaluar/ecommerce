package br.unitins.topicos1.repository;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.unitins.topicos1.model.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }
}