package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nome
) {
    public static CategoriaResponseDTO valueOf(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}