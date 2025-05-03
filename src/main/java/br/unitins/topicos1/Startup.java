package br.unitins.topicos1;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class Startup {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        Usuario existingAdmin = (Usuario) usuarioRepository.findByEmail("admin@email.com");
        if (existingAdmin != null) {
            usuarioRepository.delete(existingAdmin);
            usuarioRepository.flush();
            System.out.println("Usuário admin existente deletado.");
        }

        Usuario admin = new Usuario();
        admin.setEmail("admin@email.com");
        admin.setNome("Higor");
        admin.setPerfil(Perfil.ADMIN);
        String password = "Admin@2025";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        admin.setSenha(hash);
        usuarioRepository.persist(admin);
        System.out.println("Usuário admin criado: " + admin.getEmail());
    }
}