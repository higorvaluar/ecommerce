package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Componente;

public record ComponenteResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        Integer estoque
) {
    public ComponenteResponseDTO(Componente componente) {
        this(
                componente.id,
                componente.getNome(),
                componente.getDescricao(),
                componente.getPreco(),
                componente.getEstoque()
        );
    }
}