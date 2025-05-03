package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Tutorial;

public record TutorialResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        ProdutoResponseDTO produto
) {
    public static TutorialResponseDTO valueOf(Tutorial tutorial) {
        return new TutorialResponseDTO(
                tutorial.getId(),
                tutorial.getTitulo(),
                tutorial.getConteudo(),
                new ProdutoResponseDTO(
                        tutorial.getProduto().getId(),
                        tutorial.getProduto().getNome(),
                        tutorial.getProduto().getDescricao(),
                        tutorial.getProduto().getPreco(),
                        tutorial.getProduto().getEstoque(),
                        tutorial.getProduto().getCategoria().getId()
                )

        );
    }
}
