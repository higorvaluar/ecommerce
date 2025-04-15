package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Kit;

import java.util.List;

public record KitResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    List<ProdutoResponseDTO> produto
) {
    public KitResponseDTO(Kit kit) {
        this(
                kit.id,
                kit.getNome(),
                kit.getDescricao(),
                kit.getPreco(),
                kit.getProdutos().stream()
                        .map(ProdutoResponseDTO::new)
                        .toList()
        );
    }
}
