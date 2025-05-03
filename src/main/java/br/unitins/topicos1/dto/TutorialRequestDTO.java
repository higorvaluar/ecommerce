package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TutorialRequestDTO(
        @NotBlank(message = "O título não pode ser vazio")
        @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
        String titulo,

        @NotBlank
        String conteudo,

        @NotNull(message = "O ID do produto é obrigatório")
        ProdutoRequestDTO produto  // Garanta que o nome do campo bate com o JSON
) {}