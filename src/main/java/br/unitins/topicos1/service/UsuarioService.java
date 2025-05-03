package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioService {
    public UsuarioResponseDTO create (@Valid UsuarioRequestDTO usuarioRequestDTO);
    public void update(Long id, UsuarioRequestDTO UsuarioRequestDTO);
    public void delete(Long id);
    public UsuarioResponseDTO findById(Long id);
    public List<UsuarioResponseDTO> findAll();
    public UsuarioResponseDTO findByEmail(String email);
}
