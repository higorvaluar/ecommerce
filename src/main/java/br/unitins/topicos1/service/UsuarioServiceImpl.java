package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Tutorial;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        usuario.setPerfil(Perfil.valueOf(usuarioRequestDTO.perfil().toUpperCase()));

        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public void update(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioBanco = usuarioRepository.findById(id);
        usuarioBanco.setNome(usuarioRequestDTO.nome());
        usuarioBanco.setEmail(usuarioRequestDTO.email());
        usuarioBanco.setSenha(usuarioRequestDTO.senha());
        usuarioBanco.setPerfil(Perfil.valueOf(usuarioRequestDTO.perfil().toUpperCase()));
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository
                .listAll()
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

    public List<UsuarioResponseDTO> findAll(int page, int pageSize) {

        List<Usuario> listUsuario = usuarioRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listUsuario.stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.find("email", email).firstResult();
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado com o email: " + email);
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }
}