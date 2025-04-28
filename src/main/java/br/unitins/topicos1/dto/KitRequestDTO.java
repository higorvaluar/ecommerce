package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record KitRequestDTO(
        @NotNull(message = "O nome não pode ser nulo")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotEmpty(message = "A lista de produtos não pode ser vazia")
        List<Long> produtosIds
) {}