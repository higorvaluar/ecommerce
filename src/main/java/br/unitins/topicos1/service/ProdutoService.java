package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Categoria;
import jakarta.validation.Valid;

import java.util.List;

public interface ProdutoService {
    public ProdutoResponseDTO create(@Valid ProdutoRequestDTO produtoRequestDTO);

    public void update(Long id, ProdutoRequestDTO produtoRequestDTO);

    public void delete(Long id);

    public ProdutoResponseDTO findById(Long id);

    public List<ProdutoResponseDTO> findAll();

    public List<ProdutoResponseDTO> findByCategoria(Long categoria);

    public List<ProdutoResponseDTO> findByNome(String nome);
}
