package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.StatusPedido;
import br.unitins.topicos1.model.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record PedidoRequestDTO(
        @NotNull(message = "O ID do usuário não pode ser nulo")
        Long usuarioId,

        @NotBlank(message = "O nome do usuário não pode estar vazio")
        Usuario usuario,

        @NotNull(message = "A data não pode ser nula")
        LocalDateTime data,

        @NotNull(message = "O total não pode ser nulo")
        @PositiveOrZero(message = "O total deve ser zero ou positivo")
        Double total,

        @NotBlank(message = "O status não pode estar vazio")
        StatusPedido status,

        @NotEmpty(message = "A lista de itens não pode estar vazia")
        List<ItemPedidoRequestDTO> itens
) {
}
