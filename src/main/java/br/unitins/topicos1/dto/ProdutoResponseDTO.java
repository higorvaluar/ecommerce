package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        Integer estoque,
        CategoriaResponseDTO categoria
) {
    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.id,
                produto.nome,
                produto.descricao,
                produto.preco,
                produto.estoque,
                new CategoriaResponseDTO(produto.categoria)
        );
    }

    public record CategoriaResponseDTO(
            Long id,
            String nome
    ) {
        public CategoriaResponseDTO(br.unitins.topicos1.model.Categoria categoria) {
            this(categoria.id, categoria.nome);
        }
    }
}