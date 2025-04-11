package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Categoria;

public record CategoriaResponseDTO(Long id, String nome) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
