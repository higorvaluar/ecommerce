package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoriaService {

    public CategoriaResponseDTO create(@Valid CategoriaRequestDTO categoriaDTO);

    public void update(Long id, CategoriaRequestDTO categoriaDTO);

    public void delete(Long id);

    public CategoriaResponseDTO findById(Long id);

    public List<CategoriaResponseDTO> findAll();

    public List<CategoriaResponseDTO> findByNome(String nome);
}
