package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Tutorial;

public record TutorialResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        ProdutoResponseDTO produto
) {
    public TutorialResponseDTO(Tutorial tutorial) {
        this(
                tutorial.id,
                tutorial.getTitulo(),
                tutorial.getConteudo(),
                new ProdutoResponseDTO(tutorial.getProduto())
        );
    }
}
