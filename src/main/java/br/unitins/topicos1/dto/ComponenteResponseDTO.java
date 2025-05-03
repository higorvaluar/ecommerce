package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Componente;

public record ComponenteResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        Integer estoque
) {
    public static ComponenteResponseDTO valueOf(Componente componente) {
        return new ComponenteResponseDTO(
                componente.getId(),
                componente.getNome(),
                componente.getDescricao(),
                componente.getPreco(),
                componente.getEstoque()
        );
    }
}