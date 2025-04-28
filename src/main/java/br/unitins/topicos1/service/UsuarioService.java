package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.mindrot.jbcrypt.BCrypt;


import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(hashSenha(dto.senha()));
        usuario.setPerfil(dto.perfil() != null ? Perfil.valueOf(dto.perfil()) : Perfil.USER); // Define perfil padrão como USER

        usuarioRepository.persist(usuario);
        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return toResponseDTO(usuario);
    }

    public String autenticar(String email, String senha) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario == null) {
                throw new NotFoundException("Usuário não encontrado");
            }

            // Debug avançado
            System.out.println("=== DEBUG DETALHADO ===");
            System.out.println("Senha recebida: '" + senha + "'");
            System.out.println("Hash armazenado: '" + usuario.getSenha() + "'");
            System.out.println("Tamanho do hash: " + usuario.getSenha().length());

            // Verificação robusta
            boolean senhaValida = BCrypt.checkpw(senha.trim(), usuario.getSenha().trim());
            System.out.println("Resultado BCrypt.checkpw: " + senhaValida);

            if (!senhaValida) {
                throw new NotFoundException("Credenciais inválidas");
            }

            return gerarToken(usuario);
        } catch (Exception e) {
            System.err.println("ERRO NA AUTENTICAÇÃO: " + e.getMessage());
            throw e;
        }
    }

    private String gerarToken(Usuario usuario) {
        Set<String> roles = new HashSet<>();
        roles.add("USER");

        return Jwt.issuer("topicos1")
                .upn(usuario.getEmail())
                .groups(roles)
                .sign();
    }

    private String hashSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    private boolean verificarSenha(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
