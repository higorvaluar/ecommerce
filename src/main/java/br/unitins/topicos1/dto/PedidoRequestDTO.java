package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PedidoRequestDTO(
        @NotNull(message = "O ID do usuário não pode ser nulo")
        Long usuarioId,

        @NotEmpty(message = "A lista de itens não pode estar vazia")
        List<ItemPedidoDTO> itens
) {
    public record ItemPedidoDTO(
            @NotNull(message = "O ID do kit não pode ser nulo")
            Long kitId,

            @NotNull(message = "A quantidade não pode ser nula")
            @Positive(message = "A quantidade deve ser maior que zero")
            Integer quantidade
    ) {}
}