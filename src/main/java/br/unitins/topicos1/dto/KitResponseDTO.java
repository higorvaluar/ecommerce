package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Kit;

import java.util.List;
import java.util.stream.Collectors;

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
                kit.getProdutos() != null
                    ? kit.getProdutos().stream().map(ProdutoResponseDTO::new)
                        .collect(Collectors.toList())
                        : List.of()
        );
    }
}
