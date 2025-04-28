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
        // Passo 1: Deletar o usuário existente
        Usuario existingAdmin = usuarioRepository.findByEmail("admin@email.com");
        if (existingAdmin != null) {
            usuarioRepository.delete(existingAdmin);
            usuarioRepository.flush(); // Força a execução da exclusão no banco
            System.out.println("Usuário admin existente deletado com sucesso.");
        } else {
            System.out.println("Nenhum usuário admin existente encontrado.");
        }

        // Passo 2: Verificar se o usuário foi realmente deletado
        existingAdmin = usuarioRepository.findByEmail("admin@email.com");
        if (existingAdmin != null) {
            System.err.println("Erro: Usuário admin não foi deletado corretamente!");
            return;
        }

        // Passo 3: Criar um novo usuário admin
        try {
            Usuario admin = new Usuario();
            admin.setEmail("admin@email.com");
            admin.setNome("Higor");
            admin.setPerfil(Perfil.ADMIN);
            String newPassword = "123";
            String newHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            admin.setSenha(newHash);
            usuarioRepository.persist(admin);
            usuarioRepository.flush(); // Força a persistência
            System.out.println("Usuário admin criado: " + admin.getEmail());
            System.out.println("Senha hash gerada: " + newHash);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário admin: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao criar usuário admin", e);
        }
    }
}