package br.unitins.topicos1.dto;

public record ProdutoResponseDTO(Long id, String nome, String descricao, double preco, int estoque, Long categoriaId) {
}
