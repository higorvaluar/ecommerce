package br.unitins.topicos1.dto;

import java.util.List;

public record KitRequestDTO(
    String nome,
    String descricao,
    Double preco,
    List<Long> produtosIds
) {}
