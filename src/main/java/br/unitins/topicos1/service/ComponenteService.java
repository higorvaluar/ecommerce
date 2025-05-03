package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ComponenteRequestDTO;
import br.unitins.topicos1.dto.ComponenteResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ComponenteService {
    public ComponenteResponseDTO create(@Valid ComponenteRequestDTO componenteDTO);

    public void update(Long id, ComponenteRequestDTO componenteDTO);

    public void delete(Long id);

    public ComponenteResponseDTO findById(Long id);

    public List<ComponenteResponseDTO> findAll();

    public List<ComponenteResponseDTO> findByNome(String nome);
}
