package br.unitins.topicos1.dto;

public record ItemPedidoRequestDTO(
        String kitNome,
        Integer quantidade,
        Double precoUnitario
) {}