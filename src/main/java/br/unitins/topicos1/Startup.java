package br.unitins.topicos1;

import org.mindrot.jbcrypt.BCrypt;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Startup {

    @Inject
    UsuarioRepository usuarioRepository;

    @PostConstruct
    @Transactional
    public void init() {
        if (usuarioRepository.findByEmail("admin@email.com") == null) {
            String senhaCriptografada = BCrypt.hashpw("123", BCrypt.gensalt());

            Usuario admin = new Usuario(
                    "Administrador",
                    "admin@email.com",
                    senhaCriptografada,
                    Perfil.ADMIN
            );
            usuarioRepository.persist(admin);
            System.out.println("Usuário ADMIN criado com sucesso.");
        } else {
            System.out.println("Usuário ADMIN já existe.");
        }
    }
}