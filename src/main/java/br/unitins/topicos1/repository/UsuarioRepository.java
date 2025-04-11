package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        return find("email = ?1 AND senha = ?2", email, senha).firstResult();
    }
}
