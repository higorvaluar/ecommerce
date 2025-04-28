package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO (
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "O preço não pode ser nulo")
        @Positive(message = "O preço deve ser maior que zero")
        double preco,

        @NotNull(message = "O estoque não pode ser nulo")
        @PositiveOrZero(message = "O estoque não pode ser negativo")
        int estoque,

        @NotNull(message = "O ID da categoria não pode ser nulo")
        Long categoriaId
) {}