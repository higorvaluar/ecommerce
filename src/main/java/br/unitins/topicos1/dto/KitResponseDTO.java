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
    public static KitResponseDTO valueOf(Kit kit) {

        List<ProdutoResponseDTO> listaProduto = kit.getProdutos()
                .stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();

        return new KitResponseDTO(
                kit.getId(),
                kit.getNome(),
                kit.getDescricao(),
                kit.getPreco(),
                listaProduto
        );
    }
}
