package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.KitRequestDTO;
import br.unitins.topicos1.dto.KitResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface KitService {
    public KitResponseDTO create(@Valid KitRequestDTO kitRequestDTO);

    public void update(Long id, KitRequestDTO kitRequestDTO);

    public void delete(Long id);

    public KitResponseDTO findById(Long id);

    public List<KitResponseDTO> findAll();

    public List<KitResponseDTO> findByNome(String nome);
}
