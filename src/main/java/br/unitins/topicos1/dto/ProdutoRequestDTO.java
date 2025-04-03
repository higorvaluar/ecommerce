package br.unitins.topicos1.dto;

public record ProdutoRequestDTO (String nome, String descricao, double preco, int estoque, Long categoriaId) {
}